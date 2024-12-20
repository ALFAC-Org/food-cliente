terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 5.46"
    }
    kubernetes = {
      source  = "hashicorp/kubernetes"
      version = "~> 2.32.0"
    }
  }

  required_version = ">= 1.2.0"
}

# We don't define the provider's credentials here because we are using the AWS CLI to authenticate.
# https://registry.terraform.io/providers/hashicorp/aws/5.65.0/docs?utm_content=documentLink&utm_medium=Visual+Studio+Code&utm_source=terraform-ls#environment-variables
provider "aws" {
  region = var.aws_region
}

data "aws_eks_cluster" "food_cluster" {
  name = var.cluster_name
}

data "aws_eks_cluster_auth" "food_cluster" {
  name = var.cluster_name
}

provider "kubernetes" {
  host                   = data.aws_eks_cluster.food_cluster.endpoint
  cluster_ca_certificate = base64decode(data.aws_eks_cluster.food_cluster.certificate_authority[0].data)
  token                  = data.aws_eks_cluster_auth.food_cluster.token
}
