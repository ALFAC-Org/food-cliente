docker build --no-cache --platform linux/amd64 -f Dockerfile.prod.yml \
  --build-arg MYSQL_ROOT_PASSWORD=myrootpassword \
  --build-arg MYSQL_DATABASE=mydatabase \
  --build-arg MYSQL_USER=myuser \
  --build-arg MYSQL_PASSWORD=mypassword \
  --build-arg SPRING_CLIENTE_DATASOURCE_URL=jdbc:mysql://localhost:3307/mydatabase \
  --build-arg FOOD_CLIENTE_DATASOURCE_USERNAME=myuser \
  --build-arg FOOD_CLIENTE_DATASOURCE_PASSWORD=mypassword \
  --build-arg FOOD_CLIENTE_VERSION=latest \
  --build-arg APPLICATION_DATABASE_VERSION=latest \
  --build-arg FOOD_CLIENTE_PORT=8082 \
  -t carlohcs/food-repo:withoutdb16 .

docker push carlohcs/food-repo:withoutdb13
docker run -p 8082:8082 carlohcs/food-repo:withoutdb11


docker buildx build --platform=linux/amd64 -f Dockerfile.prod.yml \
  --build-arg MYSQL_ROOT_PASSWORD=myrootpassword \
  --build-arg MYSQL_DATABASE=mydatabase \
  --build-arg MYSQL_USER=myuser \
  --build-arg MYSQL_PASSWORD=mypassword \
  --build-arg SPRING_CLIENTE_DATASOURCE_URL=jdbc:mysql://localhost:3307/mydatabase \
  --build-arg FOOD_CLIENTE_DATASOURCE_USERNAME=myuser \
  --build-arg FOOD_CLIENTE_DATASOURCE_PASSWORD=mypassword \
  --build-arg FOOD_CLIENTE_VERSION=latest \
  --build-arg APPLICATION_DATABASE_VERSION=latest \
  --build-arg FOOD_CLIENTE_PORT=8082 \
  -t carlohcs/food-repo:withoutdb18 . --push