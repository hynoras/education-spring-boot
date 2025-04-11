# Dockerfile
# Stage 1: Build the jar
FROM gradle:8.10-jdk23 AS builder
WORKDIR /app
COPY . .
RUN gradle clean build -x test

# Stage 2: Run the app
FROM openjdk:23-jdk-slim
WORKDIR /app
COPY build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
