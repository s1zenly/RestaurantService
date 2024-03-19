package ru.hse.software.restaurant;

import ru.hse.software.restaurant.Client.controller.AdminControllerClient;
import ru.hse.software.restaurant.Client.controller.AuthControllerClient;
import ru.hse.software.restaurant.Server.controller.AdminController;
import ru.hse.software.restaurant.Server.controller.AuthController;
import ru.hse.software.restaurant.Server.service.AuthService;
import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;
import ru.hse.software.restaurant.Server.view.repository.AdminRepository;
import ru.hse.software.restaurant.Server.view.repository.UserRepository;

import java.sql.Connection;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Connection connection = DatabaseConnection.getConnection();
        SQLExecutor.executeSQLFileWithoutParams(connection, "sql/create_enum.sql");
        SQLExecutor.executeSQLFileWithoutParams(connection, "sql/create_table.sql");

        AdminControllerClient a = new AdminControllerClient();
        a.changeDish();
    }
}