package ru.hse.software.restaurant.Server.service.schedulers;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.service.schedulers.abstracts.OrderScheduler;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RequiredArgsConstructor
public class CleanIlliquidOrdersScheduler extends OrderScheduler implements Runnable {

    private List<Order> illiquidOrders;

    @Override
    public void run() {
        try {
            updateOrdersCollection();
        } catch (SQLException e) {
            throw new RuntimeException();
        }


        illiquidOrders.forEach(order -> {
            if(ChronoUnit.MINUTES.between(order.getDate().toLocalDateTime(), LocalDateTime.now()) >= 10) {
                try {
                    orderRepository.delete(order.getId());
                } catch (SQLException e) {
                    throw new RuntimeException();
                }
            }
        });
    }

    @Override
    protected void updateOrdersCollection() throws SQLException {
        List<Order> listOrders = orderRepository.getAllOrders();

        illiquidOrders = listOrders.stream()
                        .filter(order -> order.getStatus() == OrderStatuses.INACTIVE)
                        .toList();

    }
}
