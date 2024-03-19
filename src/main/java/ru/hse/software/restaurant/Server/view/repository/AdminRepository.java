package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;
import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.entity.Persona;
import ru.hse.software.restaurant.Server.view.enums.AccessTypes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository extends PersonaRepository{
    @Override
    public Admin findByEmailAndPassword(String email, String password) throws SQLException {
        if(email == null || password == null) {
            return null; // throw
        }

        Admin admin = new Admin();
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/admin/find_by_email_and_password.sql";
        Object[] params = {email, password};

        ResultSet resultSet = SQLExecutor.
                executeSQLFileWithParamsWithReturn(connection, filepath, params);

        if(resultSet == null) {
            return null;
        }

        while(resultSet.next()) {
            admin.setId(resultSet.getLong("id"));
            admin.setEmail(resultSet.getString("email"));
            admin.setPassword(resultSet.getString("password"));
            String access = resultSet.getString("access_type");
            admin.setAccessType(AccessTypes.valueOf(access.toUpperCase()));
        }

        return admin;
    }

    @Override
    public Persona findById(Long id) throws SQLException {
        return null;
    }
}
