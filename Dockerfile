FROM openjdk:8u322-slim as builder

WORKDIR /

#COPY ./pom.xml /app
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline

COPY ./src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:8u322-slim

WORKDIR /app
RUN mkdir ./logs
COPY --from=builder /target/msvc-events-0.0.1-SNAPSHOT.jar .
EXPOSE 8002

ENTRYPOINT ["java", "-jar", "msvc-events-0.0.1-SNAPSHOT.jar"]