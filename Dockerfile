# Use the official gradle 8.2.2 image as the base image
FROM gradle:8.4-jdk17 AS build

# Set environment variables
ENV APP_HOME=/app \
    APP_PORT=8080

# Set the working directory in the container
WORKDIR $APP_HOME

# Copy the Gradle project files to the container
COPY build.gradle.kts settings.gradle.kts gradlew gradle $APP_HOME/

# Set execute permissions
RUN ["chmod", "+x", "./gradlew"]

# Copy the source code to the container
COPY src $APP_HOME/src

# Run the Gradle build inside the container
RUN gradle build --no-daemon

# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-oracle

# Copy the built artifacts from the previous stage to the container
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port on which the application will run
EXPOSE $APP_PORT

# Set the entrypoint command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]