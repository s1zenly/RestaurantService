package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.database.DatabaseConnection;
import ru.hse.software.restaurant.Server.view.database.SQLExecutor;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.enums.PaymentStatusOrder;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository {

    public Order findById(long orderId) throws SQLException {

        Order order = null;
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/find_by_id.sql";

        Object[] params = {orderId};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);

        while(resultSet.next()) {
            order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setDate(resultSet.getTimestamp("date"));
            order.setPrice(resultSet.getInt("price"));
            order.setDifficult(resultSet.getInt("difficult"));
            order.setStatus(OrderStatuses.valueOf(resultSet.getString("status").toUpperCase()));
            order.setStatusPayment(PaymentStatusOrder.valueOf(resultSet.getString("payment_status").toUpperCase()));
            order.setUserId(resultSet.getLong("user_id"));
            order.setDishes(dishesWithoutDependency(orderId));
        }

        return order;
    }

    public void saveDishInOrder(long orderId, long dishId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/save_dish_in_order.sql";
        Object[] params = {orderId, dishId};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);
    }

    public void deleteDishInOrder(long orderId, long dishId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/delete_dish_in_order.sql";
        Object[] params = {orderId, dishId};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);
    }

    public void delete(long orderId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/delete.sql";
        Object[] params = {orderId};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);
    }

    public void updateCounter(long orderId, long dishId, int count) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/update_count.sql";
        Object[] params = {count, orderId, dishId};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);
    }

    public void save(long userId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/save.sql";
        Object[] params = {userId};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath,params);
    }

    public void update(Order order) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/update.sql";
        Object[] params = {order.getStatus().getValue(), order.getStatusPayment().getValue(), order.getDifficult(), order.getPrice(), order.getId()};

        SQLExecutor.executeSQLFileWithParamsWithoutReturn(connection, filepath, params);

    }

    public List<Order> getAllOrders() throws SQLException {

        List<Order> orders = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/get_all.sql";

        ResultSet resultSet = SQLExecutor.executeSQLFileWithoutParamsWithReturn(connection, filepath);

        while(resultSet.next()) {
            Order order = new Order();

            order.setId(resultSet.getLong("id"));
            order.setDate(resultSet.getTimestamp("date"));
            order.setPrice(resultSet.getInt("price"));
            order.setDifficult(resultSet.getInt("difficult"));
            order.setStatus(OrderStatuses.valueOf(resultSet.getString("status").toUpperCase()));
            order.setStatusPayment(PaymentStatusOrder.valueOf(resultSet.getString("payment_status").toUpperCase()));
            order.setUserId(resultSet.getLong("user_id"));
            order.setDishes(dishesWithoutDependency(resultSet.getInt("id")));

            orders.add(order);
        }

        return orders.isEmpty() ? null : orders;
    }

    public List<Order> getAllOrdersByUserId(long userId) throws SQLException {

        List<Order> orders = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/get_all_by_user_id.sql";
        Object[] params = {userId};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);

        while(resultSet.next()) {
            Order order = new Order();

            order.setId(resultSet.getLong("id"));
            order.setDate(resultSet.getTimestamp("date"));
            order.setPrice(resultSet.getInt("price"));
            order.setDifficult(resultSet.getInt("difficult"));
            order.setStatus(OrderStatuses.valueOf(resultSet.getString("status").toUpperCase()));
            order.setStatusPayment(PaymentStatusOrder.valueOf(resultSet.getString("payment_status").toUpperCase()));
            order.setUserId(resultSet.getLong("user_id"));
            order.setDishes(dishesWithoutDependency(order.getId()));

            orders.add(order);
        }

        return orders.isEmpty() ? null : orders;
    }

    public boolean existDishInOrder(long orderId, long dishId) throws SQLException {

        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/exist_dish_in_order.sql";
        Object[] params = {orderId, dishId};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);

        return resultSet.next();
    }

    public List<Long> orderIdBusyDish(long dishId) throws SQLException {

        List<Long> ordersId = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/find_order_in_dish.sql";
        Object[] params = {dishId};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);

        while(resultSet.next()) {
            ordersId.add(resultSet.getLong("order_id"));
        }

        return ordersId.isEmpty() ? null : ordersId;
    }

    public Integer findCountDish(long orderId, long dishId) throws SQLException {

        int quantity = 0;
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/order/find_count_dish.sql";
        Object[] params = {orderId, dishId};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);

        while(resultSet.next()) {
            quantity = resultSet.getInt("quantity");
        }

        return quantity;
    }


    private List<Dish> dishesWithoutDependency(long orderId) throws SQLException {

        List<Dish> dishList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String filepath1 = "sql/find_dish_in_order.sql";
        String filepath2 = "sql/dish/find_by_id.sql";

        Object[] params1 = {orderId};
        ResultSet dishesId = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath1, params1);

        while(dishesId.next()) {
            Dish dish = new Dish();

            Object[] params2 = {dishesId.getLong("dish_id")};
            ResultSet dishes = SQLExecutor.executeSQLFileWithParamsWithReturn
                    (connection, filepath2, params2);

            while (dishes.next()) {
                dish.setId(dishes.getLong("id"));
                dish.setTitle(dishes.getString("title"));
                dish.setPrice(dishes.getInt("price"));
                dish.setDifficult(dishes.getInt("difficult"));
            }

            dishList.add(dish);
        }

        return dishList;
    }
}
