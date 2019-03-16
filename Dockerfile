FROM maven:3.6.0-jdk-8-alpine as builder

COPY src /usr/src/app/src  
COPY pom.xml /usr/src/app  
RUN mvn -f /usr/src/app/pom.xml clean package


FROM tomcat:8-jre8-alpine

RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY --from=builder /usr/src/app/target/BIMRoad-1.0-SNAPSHOT   /usr/local/tomcat/webapps/ROOT

CMD ["catalina.sh", "run"]