package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;
import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.entity.Persona;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.enums.AccessTypes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository extends PersonaRepository{
    @Override
    public User findByEmailAndPassword(String email, String password) throws SQLException {
        if(email == null || password == null) {
            return null; // throw
        }

        User user = new User();
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/user/find_by_email_and_password.sql";
        Object[] params = {email, password};

        ResultSet resultSet = SQLExecutor.
                executeSQLFileWithParamsWithReturn(connection, filepath, params);

        if(!resultSet.next()) {
            return null;
        }

        while(resultSet.next()) {
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setMoneyAccount(resultSet.getInt("money_account"));
        }

        return user;
    }

    @Override
    public Persona findById(Long id) throws SQLException {
        return null;
    }

    public void update(User user) {} // запрос к БД на обновление данных пользователя
    public boolean save(String email, String password) throws SQLException {
        if(findByEmailAndPassword(email, password) != null) {
            return false;
        }

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/user/save.sql";
        Object[] params = {email, password};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn
                (connection, filepath, params);
        return true;
    }
}
