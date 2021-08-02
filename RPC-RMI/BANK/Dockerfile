FROM openjdk:8-jre-alpine
RUN mkdir app
WORKDIR /app
COPY ./target/BANK-1.0.0-jar-with-dependencies.jar ./
RUN chmod -x ./BANK-1.0.0-jar-with-dependencies.jar
EXPOSE 6666
CMD ["java","-jar","./BANK-1.0.0-jar-with-dependencies.jar"]