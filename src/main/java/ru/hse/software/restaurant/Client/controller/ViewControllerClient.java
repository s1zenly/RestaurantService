package ru.hse.software.restaurant.Client.controller;

import ru.hse.software.restaurant.Server.controller.ViewController;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;

import java.sql.SQLException;
import java.util.List;

public class ViewControllerClient {
    private final ViewController viewController = new ViewController();

    public List<DishDTO> getMenu() throws SQLException {
        return viewController.getAllDish();
    }
}
