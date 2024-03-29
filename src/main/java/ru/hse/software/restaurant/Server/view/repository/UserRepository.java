package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.repository.abstracts.PersonaRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRepository extends PersonaRepository {
    @Override
    public User findByEmailAndPassword(String email, String password) throws SQLException {
        if(email == null || password == null) {
            return null; // throw
        }

        User user = null;
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/user/find_by_email_and_password.sql";
        Object[] params = {email, password};

        ResultSet resultSet = SQLExecutor.
                executeSQLFileWithParamsWithReturn(connection, filepath, params);

        while(resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setMoneyAccount(resultSet.getInt("money_account"));
        }

        return user;
    }

    @Override
    public User findById(Long id) throws SQLException {

        User user = null;
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/user/find_by_id.sql";
        Object[] params = {id};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);

        while(resultSet.next()) {
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            user.setMoneyAccount(resultSet.getInt("money_account"));
        }

        return user;
    }

    public void update(User user) throws SQLException {
        long id = user.getId();
        int moneyAccount = user.getMoneyAccount();

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/user/update.sql";
        Object[] params = {moneyAccount, id};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);
    }
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
