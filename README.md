# Simple Java Project with Jaeger Tracing

This is a simple Java application that uses Jaeger for distributed tracing. The application logs and monitors HTTP GET requests.

## Prerequisites

- Java 8 or higher
- Maven
- Docker (for running Jaeger locally)

## Dependencies

- Jaeger Client 1.6.0
- Jaeger Core 1.6.0

## Running the Application

1. Start Jaeger locally using Docker:

```bash
docker run -d --name jaeger \
  -e COLLECTOR_ZIPKIN_HTTP_PORT=9411 \
  -p 5775:5775/udp \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14268:14268 \
  -p 9411:9411 \
  jaegertracing/all-in-one:1.21
```

2. Run the Java application:

```bash
mvn clean install
java -jar target/jaeger-tracing-1.0-SNAPSHOT-jar-with-dependencies.jar yourName
```

3. Open your web browser and navigate to `http://localhost:16686` to view the traces in the Jaeger UI.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
```

Please replace `my-app-1.0-SNAPSHOT.jar` with the actual name of your jar file. Also, remember to replace the version of Jaeger client and core with the latest version when you are implementing this. You can check the latest version [here](https://mvnrepository.com/artifact/io.jaegertracing/jaeger-client).
```

