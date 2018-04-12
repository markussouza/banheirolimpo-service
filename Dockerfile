FROM frolvlad/alpine-oraclejdk8:slim
MAINTAINER Markus Souza
VOLUME /tmp
ADD target/banheirolimpo-service-0.0.1-SNAPSHOT.jar banheirolimpo-service.jar
RUN sh -c 'touch /banheirolimpo-service.jar'
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
EXPOSE 8080 8787
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=dev -jar /banheirolimpo-service.jar" ]
