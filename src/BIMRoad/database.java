package BIMRoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public Connection connection;

    public database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection
                ("jdbc:mysql://0.tcp.eu.ngrok.io:11870/marketplace?allowMultiQueries=true", "database", "]i_wiI4ts,?q9Jk4");
    }

    public Connection getConnection() {
        return this.connection;
    }
}
