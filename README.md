# EVN-34 Template PoC

PoC with Kafka of some simple microservices, trying to iron out possible technical issues

## start kafka
create  docker-compose.yaml
```
version: '2'

services:

  zookeeper:
    image: strimzi/kafka:0.11.3-kafka-2.1.0
    command: [
      "sh", "-c",
      "bin/zookeeper-server-start.sh config/zookeeper.properties"
    ]
    ports:
      - "2181:2181"
    environment:
      LOG_DIR: /tmp/logs

  kafka:
    image: strimzi/kafka:0.11.3-kafka-2.1.0
    command: [
      "sh", "-c",
      "bin/kafka-server-start.sh config/server.properties --override listeners=$${KAFKA_LISTENERS} --override advertised.listeners=$${KAFKA_ADVERTISED_LISTENERS} --override zookeeper.connect=$${KAFKA_ZOOKEEPER_CONNECT}"
    ]
    depends_on:
      - zookeeper
    ports:
      - "9092:9092"
    environment:
      LOG_DIR: "/tmp/logs"
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://localhost:9092
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181
```
run docker-compose up

## How to create a template
```
mvn io.quarkus:quarkus-maven-plugin:1.8.1.Final:create \
    -DprojectGroupId=ch.helvetia \
    -DprojectArtifactId=evn-template\
    -Dextensions="" * see below
```

## Extensions
There is no one template fit all...
### Generic microservices 
```
-Dextensions="smallrye-reactive-messaging-kafka,quarkus-jsonb"
```
* smallrye-reactive-messaging-kafka: required for Kafka support [kafka](https://quarkus.io/guides/kafka)
* quarkus-jsonb: kafka serializer for POJO

### Data / Query service microservice
```
-Dextensions="smallrye-reactive-messaging-kafka,quarkus-jsonb,reactive-pg-client"
```
* smallrye-reactive-messaging-kafka: required for Kafka support [kafka](https://quarkus.io/guides/kafka)
* quarkus-jsonb: kafka serializer 
* reactive-pg-client

### Connector microservices  
```
-Dextensions="smallrye-reactive-messaging-kafka,quarkus-jsonb"
```
For example in microservice connecting to the outside or acting as endpoint
* camel-quarkus-soap: SOAP client (optional)
* resteasy-jsonb: to expose EVN object instances over Rest HTTP in the JSON format to external system [logging](https://quarkus.io/guides/logging)

Add more extensions  [Search](https://code.quarkus.io/)

### Logging
https://quarkus.io/guides/logging
Use JUL JDK java.util.logging (also called JUL)

# Quickstart

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```
then open
```
http://localhost:8080/index.html
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `kafka-quickstart-1.0-SNAPSHOT-runner.jar` file in the `/target` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/lib` directory.

The application is now runnable using `java -jar target/kafka-quickstart-1.0-SNAPSHOT-runner.jar`.

## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/kafka-quickstart-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image.