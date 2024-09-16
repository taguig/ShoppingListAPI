FROM maven:3.8.4-amazoncorretto AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM amazoncorretto
WORKDIR /app

COPY --from=build /app/target/your-app-name.jar /app/your-app-name.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/your-app-name.jar"]