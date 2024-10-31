FROM openjdk:17
COPY . /usr/scr/app
WORKDIR /usr/scr/app
CMD [ "java", "-jar", "./target/*.jar" ]