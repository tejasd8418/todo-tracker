FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/todonotification
ADD ./target/NotificationService-0.0.1-SNAPSHOT.jar /usr/src/NotificationService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","NotificationService-0.0.1-SNAPSHOT.jar"]