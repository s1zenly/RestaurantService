package ru.hse.software.restaurant.Server.controller;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.service.AuthService;
import ru.hse.software.restaurant.Server.service.OrderService;
import ru.hse.software.restaurant.Server.service.PaymentService;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.dto.UserDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.DishMapper;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.OrderMapper;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class UserController {

    private final OrderService orderService = new OrderService();
    private final PaymentService paymentService = new PaymentService();

    public boolean addDish(long orderId, String dishTitle) throws SQLException {
       return orderService.addDishInOrder(orderId, dishTitle);
    }
    public boolean deleteDish(long orderId, String dishTitle) throws SQLException {
        return orderService.deleteDishInOrder(orderId, dishTitle);
    }

    public void createNewOrder(long userId) throws SQLException {
        orderService.createOrder(userId);
    }

    public boolean makeAnOrder(long orderId) throws SQLException {
        return orderService.makeAnOrder(orderId);
    }
    public AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>> getInfoAboutOrder(long orderId) throws SQLException {
        AbstractMap.SimpleEntry<Order, Map<Dish, Integer>> order = orderService.infoAboutOrder(orderId);

        if(order == null) {
            return null;
        }

        OrderDTO orderDTO = OrderMapper.INSTANCE.toDTO(order.getKey());
        Map<DishDTO, Integer> dishes = order.getValue().entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> DishMapper.INSTANCE.toDTO(entry.getKey()),
                        Map.Entry::getValue
                ));


        return new AbstractMap.SimpleEntry<>(orderDTO, dishes);
    }

    public List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> getInfoAboutOrders(long userId) throws SQLException {
        List<AbstractMap.SimpleEntry<Order, Map<Dish, Integer>>> orders = orderService.infoAboutOrders(userId);

        if(orders == null) {
            return null;
        }

        List<AbstractMap.SimpleEntry<OrderDTO, Map<DishDTO, Integer>>> ordersDTO = new ArrayList<>();

        for(AbstractMap.SimpleEntry<Order, Map<Dish, Integer>> order : orders) {
            OrderDTO orderDTO = OrderMapper.INSTANCE.toDTO(order.getKey());
            Map<DishDTO, Integer> dishes = order.getValue().entrySet().stream()
                    .collect(Collectors.toMap(
                            entry -> DishMapper.INSTANCE.toDTO(entry.getKey()),
                            Map.Entry::getValue
                    ));

            ordersDTO.add(new AbstractMap.SimpleEntry<>(orderDTO, dishes));
        }

        return ordersDTO;
    }


    // Payment Service
    public Integer paymentOrder(long userId, long orderId, int amount) throws SQLException {
        return paymentService.paidForOrder(userId, orderId, amount);
    }

    public Integer paymentAllOrder(long userId, int amount) throws SQLException {
        return paymentService.paidAllOrder(userId, amount);
    }

    public Integer getMoneyAccount(long userId) throws SQLException {
        return paymentService.getMoneyAccount(userId);
    }

}
