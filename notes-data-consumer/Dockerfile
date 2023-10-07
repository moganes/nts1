FROM openjdk:8-jre-slim
MAINTAINER mohammadshah.alam@charter.com
VOLUME /tmp
ADD build/libs/notes-data-consumer-0.1.7.jar notes-data-consumer.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /notes-data-consumer.jar" ]