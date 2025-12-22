FROM openjdk:21
COPY . /usr/scr/app
WORKDIR /usr/scr/app
CMD [ "java", "-jar", "./target/*.jar" ]
