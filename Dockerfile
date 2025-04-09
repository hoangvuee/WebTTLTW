FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

# Use Tomcat image for runtime
FROM tomcat:10.1-jdk17

# Delete default app of tomcat
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy the built WAR file from the build stage
COPY --from=build /app/target/WebFinall-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080