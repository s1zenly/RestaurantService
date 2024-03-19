package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.enums.PaymentStatusOrder;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DishRepository {

    public Dish findByTittle(String title) throws SQLException {
        if(title == null) {
            return null; // throws
        }

        Dish dish = new Dish();
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/dish/find_by_title.sql";
        String filepath1 = "sql/find_order_in_dish.sql";
        String filepath2 = "sql/order/find_by_id";
        Object[] params = {title};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);

        if(!resultSet.next()) {
            return null;
        }

        while(resultSet.next()) {
            dish.setId(resultSet.getLong("id"));
            dish.setTitle(resultSet.getString("tittle"));
            dish.setDifficult(resultSet.getInt("difficult"));
            dish.setPrice(resultSet.getInt("price"));
            dish.setOrders(new ArrayList<>());

            Object[] params1 = {dish.getId()};
            ResultSet resultSet1 = SQLExecutor.executeSQLFileWithParamsWithReturn
                    (connection, filepath1, params1);

            while(resultSet1.next()) {
                Object[] params2 = {resultSet1.getLong("id")};
                ResultSet resultSet2 = SQLExecutor.executeSQLFileWithParamsWithReturn
                        (connection, filepath2, params2);

                Order order = new Order();
                while(resultSet2.next()) {
                    order.setId(resultSet2.getLong("id"));
                    order.setDate(resultSet2.getTimestamp("date"));
                    order.setDifficult(resultSet2.getInt("difficult"));
                    order.setPrice(resultSet2.getInt("price"));
                    order.setStatus(OrderStatuses.valueOf(resultSet2.getString("status").toUpperCase()));
                    order.setStatusPayment(PaymentStatusOrder.valueOf(resultSet2.getString("payment_status")));
                }

                dish.getOrders().add(order);
            }
        }

        return dish;
    }

    public void save(Dish dish) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/dish/save.sql";
        Object[] params = {dish.getTitle(), dish.getDifficult(), dish.getPrice()};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);
    }

    public void delete(String dishTittle) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/dish/delete.sql";
        Object[] params = {dishTittle};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);
    }

    public void update(String dishTittle, Dish dish) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/dish/update.sql";
        Object[] params = {dish.getDifficult(), dish.getPrice(), dishTittle};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);
    }
}
