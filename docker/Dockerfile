# image JDK 21
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copier le jar exécutable Spring Boot
COPY ../sakamalao-api/target/sakamalao-api-1.0-SNAPSHOT.jar app.jar

EXPOSE 8081

CMD ["java", "-jar", "app.jar"]
