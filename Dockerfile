FROM maven:3.8-jdk-11 as builder

# Copy local code to the container image.
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn package -DskipTests

FROM adoptopenjdk/openjdk11:alpine-jre

COPY --from=builder /app/target/baseProject-*.jar /baseProject.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom","-jar","/baseProject.jar"]