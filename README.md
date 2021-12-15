# BIMRoad

Online marketplace written in JavaEE based on Servlets and H2. CICD is done using Google Cloud Build and Google Cloud Run.

<img src="https://github.com/danacr/BIMRoad/blob/master/src/main/webapp/Logo.png" alt="logo" width="200"/>

Built by [Sverrevh](https://github.com/sverrevh) and me as a University project.

[![Run on Google Cloud](https://deploy.cloud.run/button.svg)](https://deploy.cloud.run/?git_repo=https://github.com/danacr/BIMRoad.git)

Alternatively, run this command inside your Google Cloud shell:

```
cloudshell_open --repo_url "https://github.com/danacr/BIMRoad.git" --page "editor"
```

Getting started:

Run: `docker-compose up`

Admin account details:

```
email: admin@bimroad.org
password: password
```

Website will be available at `localhost:8080`

Demo:
![](gifs/demo.gif)

Visiting `localhost:8082` will reveal the reverse tunnel address. (Other's can access the website at that link without the need of any port-forwarding)

Build: `docker build . --tag danacr/bimroad`

Useful commands:

```
java -jar target/dependency/webapp-runner-*.jar target/BIMRoad

# Restore
java -cp target/dependency/h2-*.jar org.h2.tools.RunScript -url jdbc:h2:file:./h2new -script sql/h2backup.sql

# Backup
java -cp target/dependency/h2-*.jar org.h2.tools.Script -url jdbc:h2:file:./h2new -script 'h2backup.sql'

# Maven
mvn install dependency:copy-dependencies
mvn install
mvn package
```
