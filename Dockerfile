FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Markus Souza

VOLUME /tmp
VOLUME /opt/banheirolimpo-service
ADD banheirolimpo-service.jar /opt/banheirolimpo-service/banheirolimpo-service.jar
RUN sh -c 'touch /opt/banheirolimpo-service.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/opt/banheirolimpo-service/banheirolimpo-service.jar"]