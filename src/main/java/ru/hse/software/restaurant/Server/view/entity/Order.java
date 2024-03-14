package ru.hse.software.restaurant.Server.view.entity;

import lombok.Data;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Date date;
    private User user;
    private OrderStatuses status;
    private List<Dish> dishes;
}
