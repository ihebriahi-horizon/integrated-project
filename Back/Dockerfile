FROM maven:3.8.4-openjdk-17 as builder

WORKDIR /app

COPY ./Back/ ./

RUN mvn dependency:go-offline

RUN mvn package

CMD ["sh", "-c", "java -jar /app/target/Back-1.0.0.jar"]
