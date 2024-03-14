package ru.hse.software.restaurant.Server.view.dto;

import lombok.Data;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Date date;
    private UserDTO user;
    private OrderStatuses status;
    private List<DishDTO> dishes;
}
