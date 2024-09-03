# Use an official Maven image to build the project
FROM maven:3.8.4-openjdk-11-slim AS build


# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY . .

# Package the Maven project (this will compile the code and package it into a JAR)
RUN mvn clean package

# Use a new stage with a lighter image to run Gatling
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built project from the previous stage
COPY --from=build /app /app

# Expose necessary ports (if needed)
EXPOSE 8030

# Run the Gatling simulation
ENTRYPOINT ["mvn", "gatling:test"]
