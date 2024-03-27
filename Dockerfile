FROM openjdk:17-jdk-alpine

COPY target/*.jar /deployments/app.jar
COPY data /data

ENTRYPOINT ["java","-jar","/deployments/app.jar"]
