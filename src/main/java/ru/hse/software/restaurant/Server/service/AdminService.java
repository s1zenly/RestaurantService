package ru.hse.software.restaurant.Server.service;


import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.DishMapper;
import ru.hse.software.restaurant.Server.view.repository.DishRepository;
import ru.hse.software.restaurant.Server.view.repository.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private final DishRepository dishRepository = new DishRepository();
    private final OrderRepository orderRepository = new OrderRepository();

    public boolean addDishInMenu(DishDTO dishDTO) throws SQLException {
        Dish dish = DishMapper.INSTANCE.toEntity(dishDTO);

        if(dishRepository.findByTitle(dish.getTitle()) != null) {
            return false;
        }

        dishRepository.save(dish);
        return true;
    }

    public boolean deleteDishInMenu(String dishTitle) throws SQLException {
        if(dishRepository.findByTitle(dishTitle) == null) {
            return false;
        }

        dishRepository.delete(dishTitle);
        return true;
    }

    public boolean changeDishInMenu(String dishTitle, DishDTO dishDTO) throws SQLException {
        Dish dish = DishMapper.INSTANCE.toEntity(dishDTO);

        if(dishRepository.findByTitle(dishTitle) == null) {
            return false;
        }

        List<Long> ordersId = orderRepository.orderIdBusyDish(dishRepository.findByTitle(dishTitle).getId());

        if(ordersId == null) {
            return true;
        }

        for(long orderId : ordersId) {
            if(orderRepository.findById(orderId).getStatus() != OrderStatuses.READY) {
                return false;
            }
        }

        dishRepository.update(dishTitle, dish);
        return true;
    }

    public DishDTO getInfo(String dishTitle) throws SQLException {
        Dish dish = dishRepository.findByTitle(dishTitle);

        return DishMapper.INSTANCE.toDTO(dish);
    }

}
