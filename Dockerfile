FROM openjdk:latest
COPY ./target/seMethods-0.1-alpha-4-jar-with-dependencies.jar /tmp
WORKDIR /tmp
ENTRYPOINT ["java", "-jar", "seMethods-0.1-alpha-4-jar-with-dependencies.jar"]