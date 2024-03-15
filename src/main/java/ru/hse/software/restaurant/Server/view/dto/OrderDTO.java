package ru.hse.software.restaurant.Server.view.dto;

import lombok.Data;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Timestamp date;
    private UserDTO user;
    private Integer difficult;
    private OrderStatuses status;
    private List<DishDTO> dishes;
}
