package ru.hse.software.restaurant.Server.view.entity;

import lombok.Data;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Timestamp date;
    private User user;
    private Integer difficult;
    private OrderStatuses status;
    private List<Dish> dishes;
}
