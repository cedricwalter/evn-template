# EVN-34 Template PoC

PoC with Kafka of some simple microservices, trying to iron out possible technical issues

# Design decisions
## 1 topic in/out and generic event 
seems to not be supported by  [kafka](https://quarkus.io/guides/kafka) but we could fork their code and force it to work...

## N topics (N = # events type), concrete or generic deserializer
* Consumer need to know which topics he need to subscribe 
* Self documenting in code: Incoming = event that trigger (=EVNÜbermitteln), Consumer write in Outgoing (=EVNErfasst)
* Alightly more complex
* As many topics or channel as events, a temporary list
    * Nachweiss-bestellung-erhalten
    * Person-als-halter-gefunden
    * Person-als-lenker-gefunden
    * Fahrzeug-standort-erfasst
    * EVN-uebermittelt
    * EVN-erstellt
    * ....

# What does this project contains

* how to define a consumer listening to any topic Incoming/Outgoing
* how to write a controller (Get/Post) and run queries
* testing of microservices: mock, spy, injection and integration test (going to controller)
* how to write a POJO and its deserializer/serializer
* how to register topics, deserializer in application.properties
* how to define a database connector and use Panache as ORM

# Open Items

* authentificatiob + autorisation -> expecting HCP running guidelines + code
* first vertical slice with multiple topics
* redirect logs to splunk (forwarder, other)
* SOAP
  * [client not supported in Quarkus yet](https://github.com/quarkusio/quarkus/issues/3479)
  * [CXF wont make it into quarkus core](https://github.com/quarkusio/quarkus/issues/4005) but [community extension](https://github.com/shumonsharif/quarkus-cxf)
  * Other method
  * [workaround to be tested](https://stackoverflow.com/questions/60218884/consume-soap-service-using-quarkus)
  * if we DON'T use native (GraalVM) [CXF Client should works fine in JVM mode](https://cxf.apache.org/docs/how-do-i-develop-a-client.html)
   
# 3rd party 
## start kafka + postgres

open a terminal in this project and run:

```docker-compose up```

kafka run and postgres also, visit http://localhost:8090, use server:db username: postgres pwd: admin

**don't use 8080 for db admin as Quarkus will listen on port 8080 by default**

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
* smallrye-reactive-messaging-kafka: required for Kafka support [kafka](https://quarkus.io/guides/kafka) / [smallrye](https://smallrye.io/smallrye-reactive-messaging/smallrye-reactive-messaging/2.2/index.html)
* quarkus-jsonb: kafka serializer for POJO
* logging: (built in) [logging](https://quarkus.io/guides/logging)

### Data / Query service microservice
```
-Dextensions="smallrye-reactive-messaging-kafka,quarkus-jsonb,reactive-pg-client,kafka-panache-quickstart"
```
* smallrye-reactive-messaging-kafka: required for Kafka support [kafka](https://quarkus.io/guides/kafka) / [smallrye](https://smallrye.io/smallrye-reactive-messaging/smallrye-reactive-messaging/2.2/index.html)
* quarkus-jsonb: kafka serializer: to read from topic and deserialize/serialize [jsonb](https://quarkus.io/guides/rest-json)
* reactive-pg-client: postgress support [postgress](https://quarkus.io/guides/reactive-sql-clients)
* kafka-panache-quickstart: hibernate support [panache](https://quarkus.io/guides/hibernate-orm-panache)
* logging: (built in) [logging](https://quarkus.io/guides/logging)

### Connector microservices  
```
-Dextensions="smallrye-reactive-messaging-kafka,quarkus-jsonb"
```
For example in microservice connecting to the outside or acting as endpoint
* camel-quarkus-soap: SOAP client (optional)
* resteasy-jsonb: to expose EVN object instances over Rest HTTP in the JSON format to external system [logging](https://quarkus.io/guides/logging)
* logging: (built in) [logging](https://quarkus.io/guides/logging)

### Add more extensions  
[Search](https://code.quarkus.io/)

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