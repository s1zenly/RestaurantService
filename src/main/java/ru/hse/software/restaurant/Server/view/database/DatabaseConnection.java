package ru.hse.software.restaurant.Server.view.database;


import ru.hse.software.restaurant.Server.configuration.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection {

    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null) {
            DatabaseConfig config = new DatabaseConfig("database.yaml");

            try {
                connection = DriverManager.getConnection(config.getUrl(), config.getUser(), config.getPassword());
            } catch(SQLException e) {
                throw new RuntimeException("Error database connection", e);
            }
        }

        return connection;
    }
}
