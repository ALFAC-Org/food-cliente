# Use a imagem com JDK e Maven para compilar a aplicacao
FROM maven:3.8.1-openjdk-17-slim AS build

# Define o WORKDIR no container
WORKDIR /app

# Copia o arquivo pom.xml para o WORKDIR
COPY pom.xml .

# Copia o código fonte para o WORKDIR
COPY src ./src
COPY config ./config

# Compila o aplicativo com o Maven
RUN mvn clean install -U

# Crie uma imagem baseada na JDK para executar a aplicacao
FROM openjdk:17-slim

# Define o WORKDIR no container
WORKDIR /app

# Copia o JAR da aplicacao para o WORKDIR
COPY --from=build /app/target/*.jar ./app.jar

# Executa a aplicacao
CMD ["java", "-Dspring.profiles.active=prod", "-Dlogging.level.root=DEBUG", "-jar", "app.jar"]

# Build e envio da imagem para o Docker Hub
# docker buildx build --platform linux/amd64,linux/arm64 -f Dockerfile -t carlohcs/food-cliente:1 . --push

# Rodando a aplicação
# docker run -it --platform linux/amd64 -p 8080:8080 carlohcs/food-cliente:1