FROM adoptopenjdk/openjdk11

WORKDIR /usr/app/

COPY target/menu-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java","-jar","app.jar"]