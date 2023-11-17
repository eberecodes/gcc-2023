FROM gradle:8.4-jdk AS build

COPY . /app

WORKDIR /app

RUN gradle clean build --no-daemon --console=plain -Dorg.gradle.project.buildDir=/app/build/libs

FROM openjdk:17-jdk-slim

EXPOSE 8080
WORKDIR /app

COPY --from=build /app/build/libs/gcc-2023-1.jar app.jar
RUN ls -la /app
RUN ls -la

RUN ["chmod", "+x", "app.jar"]
ENTRYPOINT ["java", "-jar", "app.jar"]