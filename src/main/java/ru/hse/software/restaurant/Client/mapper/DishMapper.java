package ru.hse.software.restaurant.Client.mapper;

import ru.hse.software.restaurant.Server.view.dto.DishDTO;

public class DishMapper {

    public static DishDTO map(long id, String title, int price, int difficult) {
        DishDTO dishDTO = new DishDTO();

        dishDTO.setId(id);
        dishDTO.setTitle(title);
        dishDTO.setPrice(price);
        dishDTO.setDifficult(difficult);

        return dishDTO;
    }
}
