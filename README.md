# BIMRoad

Online marketplace written in JavaEE based on Servlets and MySQL. 

<img src="https://github.com/danacr/BIMRoad/blob/master/src/main/webapp/Logo.png" alt="logo" width="200"/>

Built by [Sverrevh](https://github.com/sverrevh) and me as a University project.

Requirements:

Create the `marketplace` database in MariaDB, and the `Items` and `Users` tables (I might eventually add a provisioning script)

Run: `docker-compose up`

Website will be available at `localhost:8080`

Visiting `localhost:4040` will reveal the reverse tunnel address. (Other's can access the website at that link without the need of any port-forwarding)

Build: `docker build . --tag danacr/bimroad`
