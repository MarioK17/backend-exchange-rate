FROM maven:3.6.3-openjdk-11 AS maven_build

COPY pom.xml /tmp/

COPY src /tmp/src/

WORKDIR /tmp/

RUN mvn package -DskipTests

FROM openjdk:11-jdk
COPY --from=maven_build /tmp/target/backend-exchange-0.1.0.jar backend-exchange-0.1.0.jar
ENTRYPOINT ["java","-jar","/backend-exchange-0.1.0.jar"]