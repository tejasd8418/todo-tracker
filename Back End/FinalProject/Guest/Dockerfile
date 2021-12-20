FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/guest
ADD ./target/Guest-0.0.1-SNAPSHOT.jar /usr/src/Guest-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Guest-0.0.1-SNAPSHOT.jar"]