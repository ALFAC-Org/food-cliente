docker build --no-cache --platform linux/amd64 -f Dockerfile.prod.yml \
  --build-arg MYSQL_ROOT_PASSWORD=myrootpassword \
  --build-arg MYSQL_DATABASE=mydatabase \
  --build-arg MYSQL_USER=myuser \
  --build-arg MYSQL_PASSWORD=mypassword \
  --build-arg SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydatabase \
  --build-arg SPRING_DATASOURCE_USERNAME=myuser \
  --build-arg SPRING_DATASOURCE_PASSWORD=mypassword \
  --build-arg APPLICATION_VERSION=latest \
  --build-arg APPLICATION_DATABASE_VERSION=latest \
  --build-arg APPLICATION_PORT=8080 \
  -t carlohcs/food-repo:withoutdb16 .

docker push carlohcs/food-repo:withoutdb13
docker run -p 8080:8080 carlohcs/food-repo:withoutdb11


docker buildx build --platform=linux/amd64 -f Dockerfile.prod.yml \
  --build-arg MYSQL_ROOT_PASSWORD=myrootpassword \
  --build-arg MYSQL_DATABASE=mydatabase \
  --build-arg MYSQL_USER=myuser \
  --build-arg MYSQL_PASSWORD=mypassword \
  --build-arg SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/mydatabase \
  --build-arg SPRING_DATASOURCE_USERNAME=myuser \
  --build-arg SPRING_DATASOURCE_PASSWORD=mypassword \
  --build-arg APPLICATION_VERSION=latest \
  --build-arg APPLICATION_DATABASE_VERSION=latest \
  --build-arg APPLICATION_PORT=8080 \
  -t carlohcs/food-repo:withoutdb18 . --push