FROM openjdk:8u111-jre-alpine
VOLUME /tmp
ADD spring-cloud-aws-sample-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENV PROG_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar $PROG_OPTS" ]