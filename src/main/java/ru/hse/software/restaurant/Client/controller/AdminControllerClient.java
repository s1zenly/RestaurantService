package ru.hse.software.restaurant.Client.controller;

import com.fasterxml.jackson.core.JsonToken;
import ru.hse.software.restaurant.Server.controller.AdminController;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;

import java.sql.SQLException;

public class AdminControllerClient {
    private final AdminController adminController = new AdminController();

    public boolean addDish(DishDTO dishDTO) throws SQLException {
        return adminController.addDish(dishDTO);
    }

    public boolean deleteDish(String dishTitle) throws SQLException {
        return adminController.deleteDish(dishTitle);
    }

    public boolean changeDish(String dishTitle, DishDTO dishDTO) throws SQLException {
        return adminController.changeDish(dishTitle, dishDTO);
    }

    public DishDTO getInfoAboutDish(String dishTitle) throws SQLException {
        return adminController.getInfoAboutDish(dishTitle);
    }
}
