FROM openjdk:21-jdk
WORKDIR /temp-sensor
COPY target/temp-sensor.jar temp-sensor.jar
ENTRYPOINT ["java", "-jar", "temp-sensor.jar"]