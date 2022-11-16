FROM openjdk:11.0.6-jre-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} sample.jar
ENTRYPOINT ["java","-jar","/sample.jar"]