FROM cjmason8/ubuntu-java8:latest

RUN apt-get update
RUN apt-get install -y curl

RUN mkdir /app
COPY target/authService-0.0.1-SNAPSHOT.jar /app/authService.jar

RUN sh -c 'touch /app/authService.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/authService.jar"]
