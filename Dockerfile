FROM maven:3.6.3-openjdk-11

LABEL maintainer = "hesham.osman28@gmail.com"

EXPOSE 8080

ENV db-url=
ENV db-username=
ENV db-password=

ADD . ~/users-app

WORKDIR ~/users-app
RUN mvn dependency:go-offline
RUN mvn package

WORKDIR ./target


CMD exec java -Ddb-url=${db-url} -Ddb-username=${db-username} -Ddb-password=${db-password}  -jar orange-*.jar