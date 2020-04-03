package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseCommunicator {

    protected Connection connection;

    public DatabaseCommunicator(Connection connection){
        this.connection = connection;
    }
}
