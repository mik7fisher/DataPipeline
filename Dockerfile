# Usa una imagen base de Java
FROM openjdk:17-jdk-alpine

# Define el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copia el archivo JAR de la aplicación en el contenedor
COPY target/spring-boot-backend-apirest-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto en el que la aplicacion se ejecuta (puerto 8080 para Spring Boot por defecto)
EXPOSE 8080

# Define el comando para ejecutar tu aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
