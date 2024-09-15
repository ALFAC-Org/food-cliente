#!/bin/bash

# Verifica se o método foi passado como argumento
if [ -z "$1" ]; then
  echo "[terraform] Erro: Nenhum método especificado (plan, apply, etc.)."
  exit 1
fi

METHOD=$1

terraform $METHOD \
  -var "environment=${ENVIRONMENT}" \
  -var "image_version=${BUILD_VERSION}" \
  -var "image_name=${IMAGE_NAME}" \
  -var "app_port=${APP_PORT}" \
  -var "aws_region=${AWS_REGION}" \
  -var "aws_access_key=${AWS_ACCESS_KEY_ID}" \
  -var "aws_secret_key=${AWS_SECRET_ACCESS_KEY}" \
  -var "aws_session_token=${AWS_SESSION_TOKEN}" \
  -var "node_role_arn=${NODE_ROLE_ARN}" \
  -var "kubernetes_namespace=${KUBERNETES_NAMESPACE}" \
  -var "cluster_name=${CLUSTER_NAME}"

