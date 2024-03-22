package ru.hse.software.restaurant;

import ru.hse.software.restaurant.Server.service.SchedulerService;
import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;

import java.sql.Connection;
import java.sql.SQLException;

public class StartInitialization {

    public static void initialization() throws Exception {
        initializationDatabase();
        initializationSchedulerService();
    }



    private static void initializationDatabase() throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        SQLExecutor.executeSQLFileWithoutParams(connection, "sql/create_enum.sql");
        SQLExecutor.executeSQLFileWithoutParams(connection, "sql/create_table.sql");
    }

    private static void initializationSchedulerService() {
        SchedulerService.run();
    }
}
