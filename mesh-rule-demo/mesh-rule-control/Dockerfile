FROM openjdk:8-jdk-alpine

ADD target/mesh-rule-control-1.0-SNAPSHOT.jar mesh-rule-control.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar /mesh-rule-control.jar
