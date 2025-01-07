FROM openjdk:18-jdk-slim
LABEL authors="Smirnov Denis"

WORKDIR /app

COPY target/restApi-1.0-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
