# Application configuration
variable "environment" {
  description = "The environment of the application"
  type        = string
  default     = "development"
}

variable "app_port" {
  description = "The port where the application will be listening"
  type        = string
  default     = "8080"
}

variable "image_username" {
  description = "The username of the image to deploy"
  type        = string
  default     = "carlohcs"
}

variable "enable_flyway" {
  description = "Enable Flyway to run the migrations"
  type        = bool
  default     = false
}

# Kubernetes configuration
variable "cluster_name" {
  description = "Name of the EKS Cluster"
  type        = string
  default     = "food-cluster"
}

variable "kubernetes_namespace" {
  description = "The Kubernetes namespace where the resources will be provisioned"
  type        = string
  default     = "default"
}

variable "k8s_host" {
  description = "The Kubernetes host"
  default     = ""
}

# AWS provider configuration
variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

variable "node_role_arn" {
  description = "ARN of the IAM Role that will be associated with the Node Group"
  type        = string
  sensitive   = true
}

variable "food_cliente_image_name" {
  description = "The name of the image to deploy"
  type        = string
  default     = "food-cliente"
}

variable "food_cliente_service_port" {
  description = "The port where the application will be listening"
  type        = string
  default     = "30002"
}

variable "food_cliente_image_version" {
  description = "The version of the image to deploy"
  type        = string
  default     = "latest"
}

variable "food_cliente_db_username" {
  description = "Username for the database"
  type        = string
}

variable "food_cliente_db_password" {
  description = "Password for the database"
  type        = string
  sensitive   = true
}

variable "food_cliente_db_host" {
  description = "Host for the database"
  type        = string
  default     = ""
}

variable "food_cliente_db_name" {
  description = "Name for the database"
  type        = string
  sensitive   = true
  default     = "foodclientedb"
}