# backend project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./mvnw quarkus:dev
```

## Packaging and running the application

The application can be packaged using `./mvnw package`.
It produces the `backend-1.0.0-SNAPSHOT-runner.jar` file in the `/target` directory.

The application is now runnable using `java -jar target/backend-1.0.0-SNAPSHOT-runner.jar`.

For OpenShift use you need an uber-jar as the dependencies are copied into the `target/lib` directory.
To achieve this use `./mvnw package -Duber-jar=true`.


## Creating a native executable

You can create a native executable using: `./mvnw package -Pnative`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./mvnw package -Pnative -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./target/backend-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/building-native-image-guide.
