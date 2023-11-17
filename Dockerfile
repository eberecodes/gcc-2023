FROM gradle:8.4-jdk AS build

COPY . .

RUN gradle clean build --no-daemon --console=plain -Dorg.gradle.project.buildDir=/build/libs

FROM openjdk:17-jdk-slim

EXPOSE 8080
COPY --from=build ./build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]