# syntax=docker/dockerfile:1.3-labs

FROM openjdk:8-jdk

WORKDIR /app

COPY consumer.jar ./

EXPOSE 18080
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar /app/consumer.jar"]