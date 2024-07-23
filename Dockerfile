FROM openjdk:17-alpine

WORKDIR /app

COPY build/libs/app.jar .

ENTRYPOINT ["java","-jar","app.jar"]