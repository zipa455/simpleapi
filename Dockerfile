FROM openjdk:15-alpine
COPY ./target/simpleapi-1.0.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "simpleapi-1.0.jar"]