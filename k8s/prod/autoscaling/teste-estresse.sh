#!/bin/bash
for i in {1..10000}; do
  curl http://localhost:30001/swagger-ui/index.html
  # Carga mais pesada, envolve banco de dados
  # curl http://localhost:30001/api/v1/foodcliente
  sleep $1
done
