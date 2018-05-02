FROM java:8
FROM maven:alpine

WORKDIR /app

COPY . /app

RUN mvn -v
RUN mvn clean install -DskipTests
EXPOSE 8080
LABEL maintainer="markus.souza@gmail.com"
ADD ./target/banheirolimpo-service.jar banheirolimpo-service.jar
ENTRYPOINT ["java", "-jar", "banheirolimpo-service.jar"]