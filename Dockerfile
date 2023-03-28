FROM maven:3.6.1-jdk-11-slim AS build  
COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean install package
FROM gcr.io/distroless/java  
COPY --from=build /usr/src/app/target/bradesco-frete-api-0.0.1-SNAPSHOT.jar /usr/app/bradesco-frete-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/bradesco-frete-api-0.0.1-SNAPSHOT.jar"]  