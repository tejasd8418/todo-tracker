FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/todo_db
ADD ./target/UserAuthentication-0.0.1-SNAPSHOT.jar /usr/src/UserAuthentication-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","UserAuthentication-0.0.1-SNAPSHOT.jar"]