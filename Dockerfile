# Etapa 1: Construção da aplicação com Maven
FROM maven:3.8.6-openjdk-11-slim AS build

# Definir diretório de trabalho
WORKDIR /app

# Copiar todos os arquivos do projeto para o diretório de trabalho
COPY . .

# Rodar o Maven para construir o aplicativo
RUN mvn clean install

# Etapa 2: Criar a imagem de execução com o JRE (para rodar o aplicativo)
FROM openjdk:11-jre-slim

# Definir diretório de trabalho para a execução
WORKDIR /usr/app

# Copiar o JAR gerado para dentro do container
COPY --from=build /app/target/*.jar app.jar

# Expor a porta onde o Spring Boot irá rodar (8080 por padrão)
EXPOSE 8080

# Comando para rodar o aplicativo
ENTRYPOINT ["java", "-jar", "app.jar"]
