FROM eclipse-temurin:17-alpine
WORKDIR .
COPY /target/CarPartsService-0.0.1-SNAPSHOT.jar CarPartsService.jar

ENTRYPOINT ["java","-jar","CarPartsService.jar"]