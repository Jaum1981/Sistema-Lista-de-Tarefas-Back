# Etapa 1: Usar uma imagem base com o JDK 17 para construir a aplicação
FROM maven:3.8.6-openjdk-17-slim AS build

# Definir diretório de trabalho
WORKDIR /app

# Copiar todos os arquivos do projeto para o diretório de trabalho
COPY . .

# Rodar o Maven para construir o aplicativo
RUN mvn clean install

# Etapa 2: Criar a imagem de execução com JDK 17 (usando openjdk:17-slim)
FROM openjdk:17-slim

# Definir diretório de trabalho para a execução
WORKDIR /usr/app

# Copiar o JAR gerado da etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expor a porta onde o Spring Boot vai rodar (8080 por padrão)
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
