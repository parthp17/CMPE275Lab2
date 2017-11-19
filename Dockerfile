FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/CMPE275Lab2-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]