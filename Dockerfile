FROM maven:3.6-slim

WORKDIR  /usr/src/app/

COPY pom.xml .
COPY src/ src/

RUN mvn clean package && \
    mvn dependency:copy-dependencies

COPY sql/h2backup.sql .

RUN java -cp target/dependency/h2-*.jar org.h2.tools.RunScript -url jdbc:h2:file:./h2new -script h2backup.sql

CMD java -jar target/dependency/webapp-runner-*.jar target/BIMRoad