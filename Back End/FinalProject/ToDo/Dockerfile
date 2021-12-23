FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/todo
ADD ./target/ToDo-0.0.1-SNAPSHOT.jar /usr/src/ToDo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ToDo-0.0.1-SNAPSHOT.jar"]