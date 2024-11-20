output "food_cliente_service_name" {
  value = kubernetes_service.food_cliente_service.metadata[0].name
}

output "food_cliente_service_port" {
  value = kubernetes_service.food_cliente_service.spec[0].port[0].port
}

output "food_cliente_ingress_name" {
  value = kubernetes_ingress_v1.food_cliente_ingress.metadata[0].name
}

output "food_cliente_ingress_namespace" {
  value = kubernetes_ingress_v1.food_cliente_ingress.metadata[0].namespace
}

output "food_cliente_load_balancer_host" {
  value = kubernetes_service.food_cliente_service.status[0].load_balancer[0].ingress[0].hostname
}
