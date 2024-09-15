resource "kubernetes_deployment" "deployment_food_app" {
  metadata {
    name      = "deployment-food-app"
    namespace = var.kubernetes_namespace
  }

  spec {
    selector {
      match_labels = {
        app = "deployment-food-app"
      }
    }

    template {
      metadata {
        labels = {
          app = "deployment-food-app"
        }
      }

      spec {
        // Prevent error:
        // 0/2 nodes are available: 2 node(s) were unschedulable. 
        // preemption: 0/2 nodes are available: 2 
        // Preemption is not helpful for scheduling.
        toleration {
          key      = "key"
          operator = "Equal"
          value    = "value"
          effect   = "NoSchedule"
        }

        container {
          name  = "deployment-food-app-container"
          image = "${var.image_name}:${var.image_version}"

          resources {
            requests = {
              memory: "512Mi"
              cpu: "500m"
            }
            limits = {
              memory = "1Gi"
              cpu    = "1"
            }
          }

          port {
            container_port = var.app_port
          }

          # liveness_probe {
          #   http_get {
          #     path = "/api/v2/health-check"
          #     port = var.app_port
          #   }
          #   initial_delay_seconds = 30
          #   period_seconds        = 3
          # }
        }
      }
    }
  }
}