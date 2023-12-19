FROM maven:3.8.4-openjdk-17

WORKDIR /app

COPY ./Back/ ./

RUN chmod +x ./*.sh

RUN mvn dependency:go-offline


CMD [ "./entrypoint.sh" ]