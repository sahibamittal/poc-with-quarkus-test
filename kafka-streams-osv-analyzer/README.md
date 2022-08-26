# kafka-streams-osv-analyzer

This project is intended to mock the OSV analyzer processing. The analyzer has the capability to receive components from events and then display them on system out.
Eventually this should be used to perform analysis by OSV in a multithreaded way to ensure high performance with two way parallelism.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```
or 
```shell script
mvn quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.
> This can be used to view the kafka topology generated
