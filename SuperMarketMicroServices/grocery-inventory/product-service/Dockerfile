FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app
# Copy the JAR file into the container
COPY target/*.jar app.jar
# Expose the port the application runs on
EXPOSE 8082
# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
