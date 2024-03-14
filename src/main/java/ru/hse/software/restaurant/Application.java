package ru.hse.software.restaurant;

import ru.hse.software.restaurant.Server.schedulers.OrderScheduler;
import ru.hse.software.restaurant.Server.service.SchedulerService;
import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Application {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Connection connection = DatabaseConnection.getConnection();
        SQLExecutor.executeSQLFileWithoutParams(connection, "sql/create_enum.sql");
        SQLExecutor.executeSQLFileWithoutParams(connection, "sql/create_table.sql");
    }
}