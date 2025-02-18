# Java Challenge

This project is a REST API that provides the basic functionalities of a distributed calculator, using Spring Boot, 
Apache Kafka for communication between modules, Docker for deployment and MDC for request tracking.

This challenge was proposed by WIT.

# Technologies used

This project was developed in IntelliJ.
This project was developed using Java 17+ as the main language and Spring Boot 3.1.0 to facilitate the creation of the REST API and the structuring of the modules. 
Communication between services is done using Apache Kafka, dependency management is done using Gradle, and the application can be run using Docker Desktop. 
For structured logging, we use SLF4J + Logback, and automated tests are implemented with JUnit 5.
In addition, I use MDC (Mapped Diagnostic Context) to track each request throughout its execution.

# How to Run the App

1. Open 'Docker Desktop'. You can download it here - https://docs.docker.com/get-started/get-docker/
2. Open a terminal in the root of the project and run the following command: docker-compose up -d
3. Make sure that all the containers by running: docker ps
4. In IntelliJ, go the classes 'CalculatorApplication' and 'RestApplication' and right-click on them and click 'Run'.
5. The API REST should be available in: http://localhost:8080 - You're good to go!
6. Open 'Windows Powershell', go to the root of the project and you can try the following command: curl.exe "http://localhost:8080/calculator/sum?a=10&b=5" - You should see the result.
7. After that, to shutdown the containers make sure to type this command: docker-compose down.

# Logs and Request Tracking

Each request receives a unique requestId, which is propagated in the logs and Kafka messages. To view the logs in real time:
docker logs kafka --follow

Unit and integration tests can be carried out with: gradlew.bat test (using Windows)

# Thank You!
This project was very challenging but fun, 
I took it as if it were a serious job to deliver to the client where I dedicated the necessary hours not only to study, 
but to research, test and deliver with the best possible quality. 

Thank you for testing and sending me this challenge!

Miguel Rebelo
