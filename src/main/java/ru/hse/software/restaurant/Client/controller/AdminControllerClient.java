package ru.hse.software.restaurant.Client.controller;

import com.fasterxml.jackson.core.JsonToken;
import ru.hse.software.restaurant.Server.controller.AdminController;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;

import java.sql.SQLException;

public class AdminControllerClient {
    private final AdminController adminController = new AdminController();

    public void addDish() throws SQLException {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setTitle("water");
        dishDTO.setDifficult(10);
        dishDTO.setPrice(100);
        System.out.println(adminController.addDish(dishDTO));
    }

    public void deleteDish() throws SQLException {
        System.out.println(adminController.deleteDish("water"));
    }

    public void changeDish() throws SQLException {
        DishDTO dishDTO = new DishDTO();
        dishDTO.setDifficult(1783);
        dishDTO.setPrice(10);
        System.out.println(adminController.changeDish("water", dishDTO));
    }
}
