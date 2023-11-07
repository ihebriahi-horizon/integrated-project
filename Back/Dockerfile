# Stage 1: Build the application
FROM maven:3.6.3-openjdk-11 as builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src

RUN mvn package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=builder /app/target/your-spring-boot-app-3.1.5.jar ./app.jar

CMD ["java", "-jar", "app.jar"]
