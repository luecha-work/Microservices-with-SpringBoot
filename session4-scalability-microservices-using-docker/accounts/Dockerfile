#Start with a base image containing Java runtime
FROM openjdk:23-jdk-slim

#Information about the maintainer image
MAINTAINER workshop-pringboot-account.com

#Add the apllication's jar to the image
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#execute the application
ENTRYPOINT["java", "-jar", "/accounts-0.0.1-SNAPSHOT.jar"]