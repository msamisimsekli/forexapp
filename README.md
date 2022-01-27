# forexapp
Simple foreign exchange system

## Architecture

There are 1 domain model classes

1. Conversion - Represents the conversion from base(source) currency to symbol(target) currency

There are 2 controller

1. ExchangeRateController - Able to get exchange rate between base and symbol currencies. Uses api.exchangeratesapi.io/v1/. Since I have apikey for free plan, I send a base request to get all currencies based on EUR
2. ConversionController - Able to handle new incoming conversion request, returning conversion(s) with pages and filter them between dates.

### Technology Stack
Java 11, Maven, Spring Boot 2.6.2, H2, Docker, Springdoc

### How To Run

Prereq: Docker should be installed in your system.

After navigating the folder where Dockerfile exists, you can run these commands one by one:

`docker build -t forexapp:latest` to create docker image name forexapp and tag with latest

`docker run -p 8181:8181 ebookstore:latest` to create and run a container from the image we just created

### Postman Requests

They can be found in the file `ForexApp.postman_collection`

### Springdoc

Can be found at localhost:8181/swagger-ui.html
