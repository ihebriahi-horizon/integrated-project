FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

COPY ./Back/ ./

RUN chmod +x ./entrypoint.sh
RUN chmod +x ./mvnw.sh

RUN mvn dependency:go-offline
