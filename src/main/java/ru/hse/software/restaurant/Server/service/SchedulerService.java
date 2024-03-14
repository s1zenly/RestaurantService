package ru.hse.software.restaurant.Server.service;

import ru.hse.software.restaurant.Server.schedulers.OrderScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerService {
    private static final int count = 1;
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(count);

    public static void run() {
        executorService.scheduleAtFixedRate(new OrderScheduler(), 0, 1, TimeUnit.SECONDS);
    }

    public static void exit() {
        executorService.shutdown();
    }
}
