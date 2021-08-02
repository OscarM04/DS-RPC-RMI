FROM maven:3.6.3-jdk-8 AS build
RUN mkdir app
WORKDIR /app
COPY . .
RUN mvn clean package