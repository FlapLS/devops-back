FROM  maven:3.9.6-amazoncorretto-17
ADD target/LabOneBackend-0.0.1-SNAPSHOT.jar LabOneBackend.jar
ENTRYPOINT ["java", "-jar", "LabOneBackend.jar"]
