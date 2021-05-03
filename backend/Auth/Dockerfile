FROM openjdk:8-jdk-alpine AS builder
COPY mvnw .
COPY mvnw.cmd .
COPY src src
COPY pom.xml .
RUN chmod +x ./mvnw
RUN ./mvnw package

FROM openjdk:8-jdk-alpine
COPY --from=builder target/*.jar app.jar

EXPOSE 9999
ENTRYPOINT ["java","-jar","/app.jar"]
