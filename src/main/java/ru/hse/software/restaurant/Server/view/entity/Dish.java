package ru.hse.software.restaurant.Server.view.entity;

import lombok.Data;

import java.util.List;

@Data
public class Dish {
    private Long id;
    private String title;
    private Integer price;
    private Integer difficult;
    private List<Order> orders;
}
