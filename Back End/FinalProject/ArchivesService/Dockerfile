FROM openjdk:11-jre-slim
WORKDIR usr/src
ENV MONGO_URI=mongodb://localhost:27017/archives
ADD ./target/ArchivesService-0.0.1-SNAPSHOT.jar /usr/src/ArchivesService-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ArchivesService-0.0.1-SNAPSHOT.jar"]
