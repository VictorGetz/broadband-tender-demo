FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG DEPENDENCY
COPY ${DEPENDENCY}/*.jar broadband-tender-demo.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/broadband-tender-demo.jar"]