FROM gradle:8.4-jdk AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY gradlew .
COPY gradle ./gradle
COPY src ./src

RUN gradle clean build --no-daemon

FROM adoptopenjdk/openjdk17:alpine-slim

EXPOSE 8080
COPY --from=build /build/libs/gcc-2023-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]