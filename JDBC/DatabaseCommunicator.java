package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCommunicator {
    private static final String JDBC_URL = "jdbc:sqlite:/home/jules/MyTab/src/Tap.db";

    protected Connection connection;

    public DatabaseCommunicator(){
        try {
            connection = DriverManager.getConnection(JDBC_URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void reestablishConnection(){
        try {
            connection.close();
            connection = DriverManager.getConnection(JDBC_URL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
