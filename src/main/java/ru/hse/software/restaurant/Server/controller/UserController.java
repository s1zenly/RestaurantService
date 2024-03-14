package ru.hse.software.restaurant.Server.controller;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.service.AuthService;
import ru.hse.software.restaurant.Server.service.OrderService;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.dto.UserDTO;

import java.util.List;

@RequiredArgsConstructor
public class UserController {

    private final OrderService orderService;

    public void addDish(long idUser, DishDTO dishDTO) {

    }
    public void deleteDish(long idUser, DishDTO dishDTO) {

    }

    public void makeAnOrder(long idUser, List<DishDTO> dishesDTO) {

    }

    public OrderDTO getInfoOrders(long idUser) {
        return null;
    }



}
