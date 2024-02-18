FROM amazoncorretto:21-alpine-jdk
COPY build/libs/spring-rinha-2024-q1-0.0.1-SNAPSHOT.jar spring-rinha-2024-q1.jar
ENTRYPOINT ["java","-jar","/spring-rinha-2024-q1.jar"]

# docker build -t klimberdev/rinha-2024q1-crebito .
