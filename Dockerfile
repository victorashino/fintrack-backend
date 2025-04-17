# Usar uma imagem oficial do Gradle para construir o projeto
FROM gradle:7.6-jdk21 AS builder

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar os arquivos do projeto para dentro do container
COPY . .

# Executar o comando Gradle para build
RUN gradle build -x test

# Usar uma imagem oficial do OpenJDK para rodar a aplicação
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado da etapa de build
COPY --from=builder /app/build/libs/*.jar app.jar

# Expôr a porta 8080
EXPOSE 8080

# Comando para rodar o Spring Boot
CMD ["java", "-jar", "app.jar"]
