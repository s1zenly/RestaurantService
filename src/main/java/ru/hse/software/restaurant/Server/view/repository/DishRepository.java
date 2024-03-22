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
import java.util.List;

public class DishRepository {

    public Dish findByTitle(String title) throws SQLException {
        if(title == null) {
            return null; // throws
        }

        Dish dish = null;
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/dish/find_by_title.sql";
        Object[] params = {title};

        ResultSet resultSet = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath, params);


        while(resultSet.next()) {
            dish = new Dish();
            dish.setId(resultSet.getLong("id"));
            dish.setTitle(resultSet.getString("title"));
            dish.setDifficult(resultSet.getInt("difficult"));
            dish.setPrice(resultSet.getInt("price"));
            dish.setOrders(new ArrayList<>());
            dish.setOrders(ordersWithoutDependency(dish.getId()));
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

    public List<Dish> getAllDish() throws SQLException {

        List<Dish> dishList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String filepath = "sql/dish/get_all.sql";

        ResultSet resultSet = SQLExecutor.executeSQLFileWithoutParamsWithReturn(connection, filepath);

        while(resultSet.next()) {
            Dish dish = new Dish();

            dish.setId(resultSet.getLong("id"));
            dish.setTitle(resultSet.getString("title"));
            dish.setPrice(resultSet.getInt("price"));
            dish.setDifficult(resultSet.getInt("difficult"));

            dishList.add(dish);
        }

        return dishList.isEmpty() ? null : dishList;
    }

    private List<Order> ordersWithoutDependency(long dishId) throws SQLException {

        List<Order> orderList = new ArrayList<>();
        Connection connection = DatabaseConnection.getConnection();
        String filepath1 = "sql/find_order_in_dish.sql";
        String filepath2 = "sql/order/find_by_id.sql";

        Object[] params1 = {dishId};
        ResultSet ordersId = SQLExecutor.executeSQLFileWithParamsWithReturn
                (connection, filepath1, params1);

        while(ordersId.next()) {
            Order order = new Order();

            Object[] params2 = {ordersId.getLong("order_id")};
            ResultSet orders = SQLExecutor.executeSQLFileWithParamsWithReturn
                    (connection, filepath2, params2);

            while(orders.next()) {
                order.setId(orders.getLong("id"));
                order.setDate(orders.getTimestamp("date"));
                order.setDifficult(orders.getInt("difficult"));
                order.setPrice(orders.getInt("price"));
                order.setStatus(OrderStatuses.valueOf(orders.getString("status").toUpperCase()));
                order.setStatusPayment(PaymentStatusOrder.valueOf(orders.getString("payment_status").toUpperCase()));
            }

            orderList.add(order);
        }

        return orderList;
    }

}
