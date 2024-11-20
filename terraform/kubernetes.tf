resource "kubernetes_secret" "secret_food" {
  metadata {
    name = "secret-food-cliente"
  }

  type = "Opaque"

  data = {
    APPLICATION_DATABASE_VERSION     = "latest"
    APPLICATION_PORT                 = var.app_port
    ENABLE_FLYWAY                    = var.enable_flyway
    FOOD_CLIENTE_VERSION             = var.food_cliente_image_version
    FOOD_CLIENTE_PORT                = var.food_cliente_service_port
    FOOD_CLIENTE_DATASOURCE_USERNAME = var.food_cliente_db_username
    FOOD_CLIENTE_DATASOURCE_PASSWORD = var.food_cliente_db_password
  }

  lifecycle {
    prevent_destroy = false
  }
}

resource "kubernetes_config_map" "cm_food" {
  metadata {
    name = "cm-food-cliente"
  }

  # TODO: Quando tivermos as configurações de banco, precisamos adaptar aqui
  data = {
    SPRING_CLIENTE_DATASOURCE_URL = "jdbc:mysql://${var.food_cliente_db_host}:3306/${var.food_cliente_db_name}"
  }

  lifecycle {
    prevent_destroy = false
  }
}

# FOOD CLIENTE
resource "kubernetes_deployment" "deployment_food_cliente" {
  metadata {
    name      = "deployment-food-cliente"
    namespace = var.kubernetes_namespace
  }

  spec {
    selector {
      match_labels = {
        app = "deployment-food-cliente"
      }
    }

    template {
      metadata {
        labels = {
          app = "deployment-food-cliente"
        }
      }

      spec {
        toleration {
          key      = "key"
          operator = "Equal"
          value    = "value"
          effect   = "NoSchedule"
        }

        container {
          name  = "deployment-food-cliente-container"
          image = "${var.image_username}/${var.food_cliente_image_name}:${var.food_cliente_image_version}"

          resources {
            requests = {
              memory : "512Mi"
              cpu : "500m"
            }
            limits = {
              memory = "1Gi"
              cpu    = "1"
            }
          }

          env_from {
            config_map_ref {
              name = kubernetes_config_map.cm_food.metadata[0].name
            }
          }

          env_from {
            secret_ref {
              name = kubernetes_secret.secret_food.metadata[0].name
            }
          }

          port {
            container_port = var.app_port
          }
        }
      }
    }
  }

  # TODO: verificar uma alternativa
  # depends_on = [aws_eks_node_group.food_node_group]
}

resource "kubernetes_service" "food_cliente_service" {
  metadata {
    name      = "service-food-cliente"
    namespace = var.kubernetes_namespace
    annotations = {
      "service.beta.kubernetes.io/aws-load-balancer-type" : "nlb",
      "service.beta.kubernetes.io/aws-load-balancer-scheme" : "internal",
      "service.beta.kubernetes.io/aws-load-balancer-cross-zone-load-balancing-enabled" : "true"
    }
  }
  spec {
    selector = {
      app = "deployment-food-cliente"
    }
    port {
      # LoadBalancer -> :8080
      port = var.app_port
      # Porta do Container -> :8080
      target_port = var.app_port
      # Porta do Serviço -> :30002
      node_port = var.food_cliente_service_port
    }
    type = "LoadBalancer"
  }
}

resource "kubernetes_ingress_v1" "food_cliente_ingress" {
  metadata {
    name      = "ingress-food-cliente"
    namespace = var.kubernetes_namespace
  }

  spec {
    default_backend {
      service {
        name = kubernetes_service.food_cliente_service.metadata[0].name
        port {
          number = kubernetes_service.food_cliente_service.spec[0].port[0].port
        }
      }
    }
  }
}

data "kubernetes_service" "food_cliente_service_data" {
  metadata {
    name      = kubernetes_service.food_cliente_service.metadata[0].name
    namespace = kubernetes_service.food_cliente_service.metadata[0].namespace
  }
}
