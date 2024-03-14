package ru.hse.software.restaurant.Server.view.database;

import ru.hse.software.restaurant.Server.configuration.DatabaseConfig;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class SQLExecutor {

    public static void executeSQLFileWithoutParams(Connection connection, String filepath) throws SQLException {
        String sql = getReader(filepath).lines().collect(Collectors.joining("\n"));
        Statement statement = connection.createStatement();

        statement.execute(sql);
    }

    public static void executeSQLFileWithParams
            (Connection connection, String filepath, Object... params) throws SQLException{

        String sql = getReader(filepath).lines().collect(Collectors.joining("\n"));
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        for(int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }

        preparedStatement.execute();
    }

    private static BufferedReader getReader(String filepath) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(
                    DatabaseConnection.class.getClassLoader().getResourceAsStream(filepath)));
        } catch (Exception e) {
            throw new RuntimeException("Error execution SQL file", e);
        }

        return reader;
    }
}
