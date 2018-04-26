FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Markus Souza
EXPOSE 8080
ADD target/banheirolimpo-service.jar banheirolimpo-service.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Dspring.profiles.active=dev -jar /banheirolimpo-service.jar" ]
