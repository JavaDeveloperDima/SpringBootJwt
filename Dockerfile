FROM openjdk:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

COPY entrypoint.sh entrypoint.sh

RUN apt-get update && apt-get install -y netcat-openbsd

RUN chmod +x entrypoint.sh

ENTRYPOINT ["/entrypoint.sh"]

