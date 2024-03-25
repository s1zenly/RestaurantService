package ru.hse.software.restaurant.Server.service;

import ru.hse.software.restaurant.Server.service.schedulers.CleanIlliquidOrdersScheduler;
import ru.hse.software.restaurant.Server.service.schedulers.ExecutionOrdersScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerService {
    private static final int count = 2;
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(count);

    public static void run() {
        executorService.scheduleAtFixedRate(new ExecutionOrdersScheduler(), 0, 1, TimeUnit.SECONDS);
        executorService.scheduleAtFixedRate(new CleanIlliquidOrdersScheduler(), 0, 10, TimeUnit.MINUTES);
    }

    public static void exit() {
        executorService.shutdown();
    }
}
