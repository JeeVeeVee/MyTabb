package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    private static final String JDBC_URL = "jdbc:sqlite:/home/jules/MyTab/src/Tap.db";
    private Connection connection;

    public ConnectionProvider(){
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
