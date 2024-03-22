package ru.hse.software.restaurant.Client.controller;

import ru.hse.software.restaurant.Server.controller.UserController;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.entity.Order;

import java.sql.SQLException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class UserControllerClient {
    private final UserController userController = new UserController();

    public boolean addDish(long orderId, String dishTitle) throws SQLException {
        return userController.addDish(orderId, dishTitle);
    }

    public boolean deleteDish(long orderId, String dishTitle) throws SQLException {
        return userController.deleteDish(orderId, dishTitle);
    }

    public void createNewOrder(long userId) throws SQLException {
        userController.createNewOrder(userId);
    }

    public boolean startOrder(long orderId) throws SQLException {
        return userController.makeAnOrder(orderId);
    }

    public AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>> getInfoAboutOrder(long orderId) throws SQLException {
        return userController.getInfoAboutOrder(orderId);
    }

    public List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> getInfoAboutOrders(long userId) throws SQLException {
        return userController.getInfoAboutOrders(userId);
    }

    public Integer paymentOrder(long userId, long orderId, int amount) throws SQLException {
        return userController.paymentOrder(userId, orderId, amount);
    }

    public Integer paymentAllOrder(long userId, int amount) throws SQLException {
        return userController.paymentAllOrder(userId, amount);
    }

    public Integer getMoneyAccount(long userId) throws SQLException {
        return userController.getMoneyAccount(userId);
    }
}
