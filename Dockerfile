FROM amazoncorretto:21-alpine3.21-jdk
WORKDIR /app
COPY target/*.jar app.jar
CMD ["java", "-jar", "app.jar"]