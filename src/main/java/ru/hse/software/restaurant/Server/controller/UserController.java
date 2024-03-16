package ru.hse.software.restaurant.Server.controller;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.service.AuthService;
import ru.hse.software.restaurant.Server.service.OrderService;
import ru.hse.software.restaurant.Server.service.PaymentService;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.dto.UserDTO;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.OrderMapper;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserController {

    private final OrderService orderService;
    private final PaymentService paymentService;

    public boolean addDish(long orderId, DishDTO dishDTO) {
       return orderService.addDishInOrder(orderId, dishDTO);
    }
    public boolean deleteDish(long orderId, DishDTO dishDTO) {
        return orderService.deleteDishInOrder(orderId, dishDTO);
    }

    public void createNewOrder(long userId) {
        orderService.createOrder(userId);
    }
    public boolean makeAnOrder(long orderId) {
        return orderService.makeAnOrder(orderId);
    }
    public OrderDTO getInfoAboutOrder(long orderId) {
        Order order = orderService.infoAboutOrder(orderId);

        return OrderMapper.INSTANCE.toDTO(order);
    }

    public List<OrderDTO> getInfoAboutOrders(long userId) {
        List<Order> orders = orderService.infoAboutOrders(userId);

        return orders.stream()
                .map(OrderMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());
    }


    // Payment Service
    public Integer paymentOrder(long userId, long orderId, int amount) {
        return paymentService.paidForOrder(userId, orderId, amount);
    }

    public Integer paymentAllOrder(long userId, int amount) {
        return paymentService.paidAllOrder(userId, amount);
    }

    public Integer getMoneyAccount(long userId) {
        return paymentService.getMoneyAccount(userId);
    }

}
