package ru.hse.software.restaurant;

import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Connection connection = DatabaseConnection.getConnection();
        SQLExecutor.executeSQLFileWithoutParams(connection, "sql/create_enum.sql");
        SQLExecutor.executeSQLFileWithoutParams(connection, "sql/create_table.sql");
    }
}