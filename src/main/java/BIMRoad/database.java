package BIMRoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public Connection connection;

    public database() throws ClassNotFoundException, SQLException {

        this.connection = DriverManager.getConnection("jdbc:h2:file:./h2new;FILE_LOCK=SOCKET;MODE=MYSQL;");

    }

    public Connection getConnection() {
        return this.connection;
    }
}
