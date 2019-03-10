FROM tomcat:8-jre8-alpine

RUN rm -fr /usr/local/tomcat/webapps/ROOT
COPY out/artifacts/BIMRoad_war_exploded /usr/local/tomcat/webapps/ROOT


CMD ["catalina.sh", "run"]