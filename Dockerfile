# Multi-stage build: compile with Maven Wrapper, then run with JRE
# Override base images if you need a mirror: --build-arg BASE_JDK_IMAGE=... --build-arg BASE_JRE_IMAGE=...
ARG BASE_JDK_IMAGE=eclipse-temurin:21-jdk
ARG BASE_JRE_IMAGE=eclipse-temurin:21-jre-jammy

# Build stage (JDK + Maven)
FROM ${BASE_JDK_IMAGE} AS build
WORKDIR /app
COPY . .
RUN chmod +x mvnw && ./mvnw clean package -DskipTests --no-transfer-progress

# Run stage (smaller JRE image)
FROM ${BASE_JRE_IMAGE}
WORKDIR /app
VOLUME ["/app/data"]
ENV SPRING_DATASOURCE_URL=jdbc:sqlite:/app/data/demo.db
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
