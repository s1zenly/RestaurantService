package ru.hse.software.restaurant.Server.view.entity;

import lombok.Data;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.enums.PaymentStatusOrder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Long id;
    private Timestamp date;
    private Long userId;
    private Integer difficult;
    private Integer price;
    private OrderStatuses status;
    private PaymentStatusOrder statusPayment;
    private List<Dish> dishes;
}
