FROM openjdk:8-jdk-alpine

ADD target/mesh-rule-provider-1.0-SNAPSHOT.jar mesh-rule-provider.jar
ENV JAVA_OPTS=""
ENTRYPOINT exec java $JAVA_OPTS -jar /mesh-rule-provider.jar
