FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

#--------------------------------------

FROM openjdk:17
WORKDIR /app
COPY --from=build ./app/target/*.jar ./cadpessoa-backend.jar
EXPOSE 8080
ENTRYPOINT java -jar cadpessoa-backend.jar
