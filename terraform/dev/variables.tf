# Application configuration
variable "environment" {
  description = "The environment of the application"
  type        = string
  default     = "development"
}

variable "image_version" {
  description = "The version of the image to deploy"
  type        = string
  default     = "latest"
}

variable "image_name" {
  description = "The name of the image to deploy"
  type        = string
  default     = "carlohcs/food-repo:withoutdb18"
}

variable "app_port" {
  description = "The port where the application will be listening"
  type        = number
  default     = 8080
}

# AWS provider configuration
variable "aws_region" {
  description = "AWS region"
  type        = string
  default     = "us-east-1"
}

variable "aws_access_key" {
  description = "AWS access key"
  type        = string
}

variable "aws_secret_key" {
  description = "AWS secret access key"
  type        = string
}

variable "aws_session_token" {
  description = "AWS session token"
  type        = string
}

variable "node_role_arn" {
  description = "ARN of the IAM Role that will be associated with the Node Group"
  type        = string
  sensitive   = true
}

# Kubernetes configuration
variable "kubernetes_namespace" {
  description = "The Kubernetes namespace where the resources will be provisioned"
  type        = string
  default     = "default"
}

variable "cluster_name" {
  description = "Name of the EKS Cluster"
  type        = string
  default     = "food-cluster"
}
