FROM openjdk:8u322-slim

WORKDIR /app

COPY ./target/msvc-events-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java", "-jar", "msvc-events-0.0.1-SNAPSHOT.jar"]