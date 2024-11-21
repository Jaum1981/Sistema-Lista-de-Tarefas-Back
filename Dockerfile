# Use uma imagem base do Maven para construir o projeto
FROM maven:3.8.6-jdk-11 AS build

# Copie os arquivos do projeto para dentro do container
WORKDIR /app
COPY . .

# Execute o Maven para construir o projeto
RUN mvn clean install

# Use uma imagem base do OpenJDK para executar a aplicação
FROM openjdk:11-jre-slim

# Copie o JAR do build para dentro do container
COPY --from=build /app/target/your-app-name.jar /usr/app/your-app-name.jar

# Exponha a porta em que o Spring Boot vai rodar
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
ENTRYPOINT ["java", "-jar", "/usr/app/your-app-name.jar"]
