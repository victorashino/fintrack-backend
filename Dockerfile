# Usar uma imagem oficial do OpenJDK para compilar o código
FROM openjdk:21-jdk-slim AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o arquivo de build (como o `gradle-wrapper` e os arquivos de configuração) para o container
COPY . .

# Rodar o Gradle para compilar o projeto e gerar o JAR
RUN ./gradlew clean build -x test

# Usar uma imagem mais enxuta do OpenJDK para a execução final
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado na etapa de build para a imagem final
COPY --from=build /app/build/libs/*.jar app.jar

# Expor a porta 8080 (padrão do Spring Boot)
EXPOSE 8080

# Comando para rodar a aplicação Spring Boot
CMD ["java", "-jar", "app.jar"]
