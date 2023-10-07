FROM openjdk:8-jre-alpine

VOLUME /tmp
ARG JAR_FILE
#COPY lib /lib/
COPY ${JAR_FILE} app.jar
ENTRYPOINT [""]
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
EXPOSE 8080
