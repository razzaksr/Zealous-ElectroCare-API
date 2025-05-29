.env.properties optional in application.properties when dockerize the project due to env vars will be passed via -e creation of containers
also add .env.properties in .Dockerignore since we are avoiding it Dockerfile
spring.config.import=optional:file:.env.properties

example:
Dockerfile
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

build image
docker build --no-cache -t razzaksr/zealous-electro-care .

run container
docker run -p 8081:8081 -e mysql_url=jdbc:mysql://URL -e mysql_user=USERNAME -e cors_origin=CROSS_ORIGIN_FRONT_END -e mysql_password=PASSWORD razzaksr/zealous-electro-care

push to hub
docker push razzaksr/zealous-electro-care:latest


.env.properties mandate when running in local
spring.config.import=file:.env.properties