FROM java:8
EXPOSE 8080
ADD /target/banheirolimpo-service-0.0.1.jar banheirolimpo-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "banheirolimpo-service-0.0.1.jar"]