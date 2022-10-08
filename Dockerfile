FROM maven:3-amazoncorretto-17 AS maven_build1
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp
RUN mvn package

FROM openjdk
COPY --from=maven_build1 /tmp/target/springBatch-0.0.1-SNAPSHOT.jar /data/springBatch-0.0.1-SNAPSHOT.jar
EXPOSE 8099
CMD java -jar /data/springBatch-0.0.1-SNAPSHOT.jar


