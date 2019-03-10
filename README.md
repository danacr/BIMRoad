# BIMRoad
Online marketplace written in JavaEE based on Servlets and MySQL. 

Built by [Sverrevh](https://twitter.com/Sverrevh) and me as a University project.

Requirements:

Create the `marketplace` database in MariaDB, and the `Items` and `Users` tables (I might eventually add a provisioning script)

To run:

`docker-compose up`

To Build:

Ant with IntelliJ javac2

```
ant
docker build . --tag danacr/bimroad
```