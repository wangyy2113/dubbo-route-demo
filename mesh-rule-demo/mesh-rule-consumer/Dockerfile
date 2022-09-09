FROM openjdk:8-jdk-alpine

ADD target/mesh-rule-consumer-1.0-SNAPSHOT.jar mesh-rule-consumer.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar /mesh-rule-consumer.jar
