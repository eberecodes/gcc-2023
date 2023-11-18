# Use the official OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set environment variables
ENV APP_HOME=/app \
    APP_PORT=8080

# Set the working directory in the container
WORKDIR $APP_HOME

# Copy the Gradle project files to the container
COPY build.gradle.kts settings.gradle.kts gradlew $APP_HOME/

# Set execute permissions
RUN ["chmod", "+x", "./gradlew"]

# Copy the source code to the container
COPY src $APP_HOME/src

# Run the Gradle build inside the container
RUN ./gradlew build

# Expose the port on which the application will run
EXPOSE $APP_PORT

# Set the entrypoint command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "build/libs/my-application.jar"]