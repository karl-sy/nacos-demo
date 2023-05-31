# syntax=docker/dockerfile:1.3-labs

FROM openjdk:8-jdk

WORKDIR /app

COPY provider.jar ./

EXPOSE 18081
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar /app/provider.jar"]