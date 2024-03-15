package ru.hse.software.restaurant.Server.service;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.DishMapper;
import ru.hse.software.restaurant.Server.view.repository.DishRepository;
import ru.hse.software.restaurant.Server.view.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;

    public boolean addDishInOrder(long orderId, DishDTO dishDTO) {
        Dish dish = DishMapper.INSTANCE.toEntity(dishDTO);
        Order order = orderRepository.findById(orderId);

        if(order.getStatus() == OrderStatuses.READY) {
            return false;
        }

        order.setDifficult(order.getDifficult() + dish.getDifficult());

        orderRepository.update(order);
        orderRepository.saveDishInOrder(orderId, dish.getId());

        return true;
    }

    public boolean deleteDishInOrder(long orderId, DishDTO dishDTO) {
        Dish dish = DishMapper.INSTANCE.toEntity(dishDTO);
        Order order = orderRepository.findById(orderId);

        if(order.getStatus() == OrderStatuses.READY) {
            return false;
        }

        if(!orderRepository.existDishInOrder(orderId, dish.getId())) {
            return false;
        }

        order.setDifficult(order.getDifficult() - dish.getDifficult());

        orderRepository.update(order);
        orderRepository.deleteDishInOrder(orderId, dish.getId());

        return true;
    }

    public void createOrder(long userId) {
        orderRepository.save(userId);
    }

    public boolean makeAnOrder(long orderId) {
        Order order = orderRepository.findById(orderId);
        if(order.getDishes().isEmpty()) {
            return false;
        }

        int difficult = order.getDishes().stream()
                        .mapToInt(Dish::getDifficult)
                        .sum();

        order.setDifficult(difficult);
        order.setStatus(OrderStatuses.ACCEPT);

        orderRepository.update(order);

        return true;
    }

    public Order infoAboutOrder(long orderId) {
        return orderRepository.findById(orderId);
    }
}
