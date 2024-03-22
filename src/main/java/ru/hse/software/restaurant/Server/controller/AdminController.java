package ru.hse.software.restaurant.Server.controller;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.service.AdminService;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;

import java.sql.SQLException;

public class AdminController {

    private final AdminService adminService = new AdminService();

    public boolean addDish(DishDTO dishDTO) throws SQLException {
        return adminService.addDishInMenu(dishDTO);
    }

    public boolean deleteDish(String dishTittle) throws SQLException {
        return adminService.deleteDishInMenu(dishTittle);
    }

    public boolean changeDish(String dishTittle, DishDTO dishDTO) throws SQLException {
        return adminService.changeDishInMenu(dishTittle, dishDTO);
    }

    public DishDTO getInfoAboutDish(String dishTitle) throws SQLException {
        return adminService.getInfo(dishTitle);
    }
}
