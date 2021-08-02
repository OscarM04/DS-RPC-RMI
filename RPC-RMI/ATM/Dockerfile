FROM openjdk:8-jre-alpine
RUN mkdir app
WORKDIR /app
COPY . .
RUN chmod -x ./target/ATM-1.0.0-jar-with-dependencies.jar
CMD ["java","-jar","./target/ATM-1.0.0-jar-with-dependencies.jar"]