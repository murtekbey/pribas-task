FROM openjdk:11
EXPOSE 8080
ADD target/pribas-task-app.jar pribas-task-app.jar
ENTRYPOINT ["java","jar","/pribas-task-app.jar"]