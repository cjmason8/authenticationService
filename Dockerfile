FROM adoptopenjdk/openjdk11:x86_64-alpine-jdk-11.28

RUN adduser -D -u 1000 localUser

RUN mkdir /app

RUN chown localUser /app

RUN apk update
RUN apk add curl

COPY target/authservice-0.0.1-SNAPSHOT.jar /app/authService.jar
COPY run.sh /app/run.sh

USER localUser

CMD ["/app/run.sh"]