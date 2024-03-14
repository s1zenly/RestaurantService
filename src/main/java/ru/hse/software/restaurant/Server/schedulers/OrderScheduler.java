package ru.hse.software.restaurant.Server.schedulers;


import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.entity.Order;

import java.util.ArrayDeque;
import java.util.Queue;

public class OrderScheduler implements Runnable{

    private final Queue<Order> orders = new ArrayDeque<>();

    @Override
    public void run() {
        System.out.println("Hello");
    }
}
