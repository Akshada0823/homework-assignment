# Online shopping


The `Overview` section provides general information about `Online shopping` and its working.

# Overview <a name="overview"></a>
Technologies used : Gradle , mongodb , jacoco(for test coverage)

## Building the microservice
```gradle build```

## Running the microservice with gradle
```./gradlew bootRun```

## Running the microservice with IntelliJAfter building the microservice with gradle you can run it with one of the shared run configurations:
- `local`: to run the microservice with only the `default` profile

## runs on localhost:8080

````shell
$ ../gradlew clean test -Dkarate.env=localhost
````
