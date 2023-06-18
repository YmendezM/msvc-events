FROM openjdk:8u322-slim as builder

WORKDIR /msvc-events

#COPY ./pom.xml /app
COPY ./msvc-events/.mvn ./.mvn
COPY ./msvc-events/mvnw .
COPY ./msvc-events/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./msvc-events/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /msvc-events/target/msvc-events-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

ENTRYPOINT ["java", "-jar", "msvc-events-0.0.1-SNAPSHOT.jar"]