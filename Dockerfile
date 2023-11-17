FROM gradle:8.4-jdk AS build

COPY . .

RUN gradle clean build --no-daemon

FROM openjdk:17-jdk-slim

EXPOSE 8080
COPY --from=build ./build/libs/gcc-2023-1.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]