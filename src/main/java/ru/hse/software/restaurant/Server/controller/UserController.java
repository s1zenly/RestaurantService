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
    public OrderDTO getInfoAboutOrder(long UserId) {
        Order order = orderService.infoAboutOrder(UserId);

        return OrderMapper.INSTANCE.toDTO(order);
    }


    // Payment Service
    public Integer paymentOrder(long UserId, Integer amount) {
        return null;
    }

    public Integer getMoneyAccount(long UserId) {
        return null;
    }

}
