package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;
import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.enums.AccessTypes;
import ru.hse.software.restaurant.Server.view.repository.abstracts.PersonaRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository extends PersonaRepository {
    @Override
    public Admin findByEmailAndPassword(String email, String password) throws SQLException {
        if(email == null || password == null) {
            return null; // throw
        }

        Admin admin = null;
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/admin/find_by_email_and_password.sql";
        Object[] params = {email, password};

        ResultSet resultSet = SQLExecutor.
                executeSQLFileWithParamsWithReturn(connection, filepath, params);


        while(resultSet.next()) {
            admin = new Admin();
            admin.setId(resultSet.getLong("id"));
            admin.setEmail(resultSet.getString("email"));
            admin.setPassword(resultSet.getString("password"));
            String access = resultSet.getString("access_type");
            admin.setAccessType(AccessTypes.valueOf(access.toUpperCase()));
        }

        return admin;
    }

    @Override
    public Admin findById(Long id) throws SQLException {

        Admin admin = null;
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/user/find_by_id.sql";
        Object[] params = {id};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);

        while(resultSet.next()) {
            admin = new Admin();
            admin.setId(resultSet.getLong("id"));
            admin.setEmail(resultSet.getString("email"));
            admin.setPassword(resultSet.getString("password"));
            admin.setAccessType(AccessTypes.valueOf(resultSet.getString("access_type").toUpperCase()));
        }

        return admin;
    }
}
