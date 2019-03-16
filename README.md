# BIMRoad

Online marketplace written in JavaEE based on Servlets and MySQL. 

<img src="https://github.com/danacr/BIMRoad/blob/master/src/main/webapp/Logo.png" alt="logo" width="200"/>

Built by [Sverrevh](https://github.com/sverrevh) and me as a University project.

Getting stated:

Run: `docker-compose up`

Create the `marketplace` database in MariaDB (adminer is running on `localhost:8081`) and import the `marketplace.sql`.

```
System: MySQL
Server: mariadb
Username: root
Password: password
Database: (leave blank)
```

Admin account details (becomes available after importing the sql):
```
email: admin@bimroad.org
password: password
```

Website will be available at `localhost:8080`

Visiting `localhost:8082` will reveal the reverse tunnel address. (Other's can access the website at that link without the need of any port-forwarding)

Build: `docker build . --tag danacr/bimroad`
