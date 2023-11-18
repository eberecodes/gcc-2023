FROM gradle:8.4-jdk AS build

COPY . .

RUN gradle clean bootJar --no-daemon --console=plain -Dorg.gradle.project.buildDir=/build/libs

FROM openjdk:17-jdk-slim

EXPOSE 8080
COPY /build/libs/*.jar app.jar
RUN ["chmod", "+x", "app.jar"]

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD ["-start"]