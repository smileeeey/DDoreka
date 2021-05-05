FROM openjdk:8-jdk-alpine AS builder

ADD ./build/libs/*.jar app.jar

EXPOSE 8083
ENTRYPOINT ["java","-jar","/build/libs/*.jar"]
