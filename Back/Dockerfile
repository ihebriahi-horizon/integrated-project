FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

COPY ./Back/pom.xml .
RUN mvn dependency:go-offline

COPY ./Back ./

RUN mvn package

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=builder /app/target/your-spring-boot-app-3.1.5.jar ./app.jar

CMD ["java", "-jar", "app.jar"]