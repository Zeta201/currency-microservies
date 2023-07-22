FROM openjdk:17-alpine
ADD target/*.jar naming-server.jar
ENTRYPOINT [ "java","-jar","naming-server.jar" ]