# Usa una imagen base con Java 17
FROM openjdk:21-ea-17-jdk-slim

# Copia el archivo JAR construido en el contenedor
ARG JAR_FILE=target/inatlas_api-1.0.0.jar
COPY ${JAR_FILE} inatlas_api-1.0.0.jar

# Expone el puerto en el que se ejecuta la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación al iniciar el contenedor
ENTRYPOINT ["java","-jar","/inatlas_api-1.0.0.jar"]