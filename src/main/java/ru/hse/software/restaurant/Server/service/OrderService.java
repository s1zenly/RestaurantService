package ru.hse.software.restaurant.Server.service;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.repository.DishRepository;
import ru.hse.software.restaurant.Server.view.repository.OrderRepository;

@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final DishRepository dishRepository;

    public boolean addDishInOrder() {
        return true;
    }

    public boolean deleteDishInOrder() {
        return true;
    }

    public boolean makeAnOrder() {
        return true;
    }
}
