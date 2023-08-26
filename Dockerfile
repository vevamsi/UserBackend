FROM openjdk:8
ADD target/department-service-0.0.1-SNAPSHOT.jar department-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","department-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8082