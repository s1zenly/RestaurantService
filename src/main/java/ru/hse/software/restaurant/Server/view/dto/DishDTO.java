package ru.hse.software.restaurant.Server.view.dto;

import lombok.Data;

import java.util.List;

@Data
public class DishDTO {
    private Long id;
    private String title;
    private Integer price;
    private Integer difficult;
    private List<OrderDTO> orders;
}
