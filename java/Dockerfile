# Use a Debian based image with Java 11
FROM openjdk:11-slim

# Set the working directory inside the Docker image
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven

# Copy the Maven pom.xml file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Expose the port the app runs on
EXPOSE 8080

# Start the application using Maven
CMD ["mvn", "spring-boot:run"]