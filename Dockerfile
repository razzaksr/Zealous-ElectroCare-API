# -------- Stage 1: Build the application --------
FROM maven:3.8.5-openjdk-17 AS build

# Set working directory inside the container
WORKDIR /app

# Copy only the dependency-related files to leverage Docker cache
# COPY pom.xml .
COPY . .
# RUN mvn dependency:go-offline
RUN mvn clean package

# Copy the actual source code
# COPY src ./src

# # Build the project and skip tests
# RUN mvn clean package -DskipTests

# -------- Stage 2: Create a lightweight image to run the app --------
FROM openjdk:17-jdk-slim

# Set working directory inside the final image
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8081

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
