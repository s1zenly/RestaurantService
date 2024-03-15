package ru.hse.software.restaurant.Server.schedulers.abstracts;


import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.repository.OrderRepository;

import java.util.*;

public abstract class OrderScheduler{

    protected final OrderRepository orderRepository = new OrderRepository();

    protected abstract void updateOrdersCollection();
}
