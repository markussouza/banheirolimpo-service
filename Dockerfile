FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Markus Souza
EXPOSE 8080
ADD target/banheirolimpo-service.jar banheirolimpo-service.jar
ENTRYPOINT [ "sh", "-c", "java -jar /banheirolimpo-service.jar" ]
