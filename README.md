# BIMRoad
Online marketplace written in JavaEE based on Servlets and MySQL. 

Built by [Sverrevh](https://github.com/sverrevh) and me as a University project.

Requirements:

Create the `marketplace` database in MariaDB, and the `Items` and `Users` tables (I might eventually add a provisioning script)

To run:

`docker-compose up`

Website will be available at `localhost:8080`

Visiting `localhost:4040` will reveal the reverse tunnel address. (Other's can access the website at that link without the need of any port-forwarding)

To Build:

Ant with IntelliJ javac2

```
ant
docker build . --tag danacr/bimroad
```
