# Dependency Track Kafka POC

Kafka POC on Dependency Track for the Vulnerability Analysis Task

## Running the application in dev mode

To Start the kafka and zookeeper container, run the docker-compose.yml from the project root folder

poc-with-quarkus-test/docker-compose up -d

To Start individual, run the following command:
./mvnw quarkus:dev -Dquarkus.http.port=<port number> -f <modulename>/pom.xml

The producer application is listening for the post call on port 8080
Send the POST message similar to below from Postman:
{
"eventType": 1,
"project": "p1",
"components": [
"c1",
"c2",
"c3"
]
}


url:http://localhost:8080/event


You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/kafka-streams-snyk-analyzer-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Architecture

![](/Users/sahibamittal/Desktop/poc.excalidraw.png)

