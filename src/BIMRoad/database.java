package BIMRoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public Connection connection;

    public database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection
                ("jdbc:mysql://loclahost:3306/marketplace?useSSL=false&allowMultiQueries=true", "username", "password");

    }

    public Connection getConnection() {
        return this.connection;
    }
}
