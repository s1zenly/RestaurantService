package ru.hse.software.restaurant.Server.schedulers;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.schedulers.abstracts.OrderScheduler;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CleanIlliquidOrdersScheduler extends OrderScheduler implements Runnable {

    private List<Order> illiquidOrders;

    @Override
    public void run() {
        updateOrdersCollection();

        illiquidOrders.forEach(order -> {
            if(ChronoUnit.MINUTES.between(order.getDate().toInstant(), LocalDateTime.now()) >= 20) {
                orderRepository.delete(order.getId());
            }
        });
    }

    @Override
    protected void updateOrdersCollection() {
        List<Order> listOrders = orderRepository.getAllOrders();

        illiquidOrders = listOrders.stream()
                        .filter(order -> order.getStatus() == OrderStatuses.INACTIVE)
                        .toList();

    }
}
