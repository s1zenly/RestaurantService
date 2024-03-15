package ru.hse.software.restaurant.Server.schedulers;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.schedulers.abstracts.OrderScheduler;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;

import java.util.*;

public class ExecutionOrdersScheduler extends OrderScheduler implements Runnable {

    private final int countCook = 5;
    private final LinkedList<Order> acceptedOrders = new LinkedList<>();

    @Override
    public void run() {
        if(acceptedOrders.size() < 5) {
            updateOrdersCollection();
        }

        int counter = 0;
        Iterator<Order> iteratorOrders = acceptedOrders.iterator();
        while(iteratorOrders.hasNext() && counter < countCook) {
            Order order = iteratorOrders.next();

            if(order.getStatus() == OrderStatuses.ACCEPT) {
                order.setStatus(OrderStatuses.PREPARE);
            }
            if(order.getDifficult() <= 0) {
                order.setStatus(OrderStatuses.READY);
                acceptedOrders.remove(order);
            }

            order.setDifficult(order.getDifficult() - 1);
            orderRepository.update(order);
            counter++;
        }
    }

    @Override
    protected void updateOrdersCollection() {
        LinkedList<Order> linkedListOrder = new LinkedList<>(orderRepository.getAllOrders());

        linkedListOrder.removeIf(order -> order.getStatus() == OrderStatuses.INACTIVE ||
                order.getStatus() == OrderStatuses.READY);

        linkedListOrder.sort(Comparator.comparing(Order::getStatus, statusesComparator)
                .thenComparing(Order::getId));

        acceptedOrders.addAll(linkedListOrder);
    }

    private final Comparator<OrderStatuses> statusesComparator = Comparator.comparingInt(status -> switch (status) {
        case PREPARE -> 1;
        case ACCEPT -> 2;
        default -> 0;
    });

}
