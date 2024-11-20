#!/bin/bash

# Carrega as variáveis do arquivo .env
if [ -f .env ]; then
    export $(grep -v '^#' .env | xargs)
else
    echo "[terraform] Erro: Arquivo .env não encontrado."
    exit 1
fi

# Verifica se o método foi passado como argumento
if [ -z "$1" ]; then
    echo "[terraform] Erro: Nenhum método especificado (plan, apply, etc.)."
    exit 1
fi

METHOD=$1
shift

PARAMS="$@"

terraform $METHOD $PARAMS \
-var "environment=$ENVIRONMENT" \
-var "image_username=$DOCKERHUB_USERNAME" \
-var "app_port=$APP_PORT" \
-var "app_service_port=$FOOD_SERVICE_PORT" \
-var "enable_flyway=$ENABLE_FOOD_CLIENTE_FLYWAY" \
-var "aws_region=$AWS_REGION" \
-var "node_role_arn=$ARN_AWS_LAB_ROLE" \
-var "k8s_host=$K8S_HOST" \
-var "kubernetes_namespace=$CLUSTER_NAMESPACE" \
-var "cluster_name=$CLUSTER_NAME" \
-var "food_cliente_image_name=$FOOD_CLIENTE_IMAGE_NAME" \
-var "food_cliente_image_version=$FOOD_CLIENTE_IMAGE_VERSION" \
-var "food_cliente_service_port=$FOOD_CLIENTE_SERVICE_PORT" \
-var "food_cliente_db_username=$DB_FOOD_CLIENT_USERNAME" \
-var "food_cliente_db_password=$DB_FOOD_CLIENT_PASSWORD" \
-var "food_cliente_db_name=$DB_FOOD_CLIENT_NAME" \
-var "food_cliente_db_host=$DB_FOOD_CLIENT_HOST_NAME"