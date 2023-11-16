FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

COPY ./Back/ ./

RUN mvn dependency:go-offline

RUN chmod +x ./entrypoint.sh
