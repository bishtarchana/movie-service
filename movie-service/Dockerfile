From openjdk:alpine

ADD build/libs/*.war app.war

EXPOSE 9090

ENTRYPOINT ["java", "-jar", "app.war"]