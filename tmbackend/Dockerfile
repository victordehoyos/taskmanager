FROM openjdk:17-jdk-slim

WORKDIR /app
COPY target/tmbackend-0.0.1-SNAPSHOT.jar tmbackend.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tmbackend.jar"]
