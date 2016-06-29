FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD dontbeshy-1.0.jar app.jar
RUN touch /app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom"," -Dspring.profiles.active=prod","-jar","/app.jar"]