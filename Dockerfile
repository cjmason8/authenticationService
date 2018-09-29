FROM openjdk:8u171-jdk-alpine3.8

RUN apk update
RUN apk add curl

RUN mkdir /app
COPY target/authservice-0.0.1-SNAPSHOT.jar /app/authService.jar

RUN sh -c 'touch /app/authService.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/authService.jar"]