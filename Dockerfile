FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21
ENV IMG_PATH=/img
EXPOSE 8080
RUN mkdir -p ${IMG_PATH}
COPY --from=build /app/target/libreria-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]