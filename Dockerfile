# Usar uma imagem oficial do OpenJDK
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR do Spring Boot para dentro do container
COPY build/libs/*.jar app.jar

# Expôr a porta 8080
EXPOSE 8080

# Comando para rodar o Spring Boot
CMD ["java", "-jar", "app.jar"]
