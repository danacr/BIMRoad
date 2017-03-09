package BIMRoad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {

    public Connection connection;

    public database() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection
                ("jdbc:mysql://bimroad.cf5jtfkl4laz.eu-west-1.rds.amazonaws.com/marketplace?allowMultiQueries=true", "administrator", "2POB7RPj4uKLPHiVFpxvlL");
    }

    public Connection getConnection() {
        return this.connection;
    }
}
