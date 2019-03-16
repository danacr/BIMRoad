package main.java.BIMRoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public Connection connection;

    public database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection
                ("jdbc:mysql://mariadb:3306/marketplace?useSSL=false&allowMultiQueries=true", "root", "password");

    }

    public Connection getConnection() {
        return this.connection;
    }
}
