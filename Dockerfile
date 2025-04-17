# Usar uma imagem leve do OpenJDK
FROM openjdk:21-jdk-slim

# Definir diretório de trabalho
WORKDIR /app

# Copiar o jar gerado
COPY build/libs/app.jar app.jar

# Expor a porta que o Spring Boot usa
EXPOSE 8080

# Comando para iniciar a aplicação
CMD ["java", "-jar", "app.jar"]
