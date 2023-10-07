FROM openjdk:8-jre-slim
MAINTAINER DL-OSS-CApps-NOTES-Support@charter.com
VOLUME /tmp
ADD build/libs/notes-api-0.0.19.jar notes-api-0.0.19.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /notes-api-0.0.19.jar" ]