package ru.hse.software.restaurant.Server.service;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.dto.DishDTO;
import ru.hse.software.restaurant.Server.view.dto.OrderDTO;
import ru.hse.software.restaurant.Server.view.entity.Dish;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.DishMapper;
import ru.hse.software.restaurant.Server.view.repository.DishRepository;
import ru.hse.software.restaurant.Server.view.repository.OrderRepository;

import java.sql.SQLException;
import java.util.*;

public class OrderService {
    private final OrderRepository orderRepository = new OrderRepository();
    private final DishRepository dishRepository = new DishRepository();

    public boolean addDishInOrder(long orderId, String dishTitle) throws SQLException {
        Dish dish = dishRepository.findByTitle(dishTitle);
        if(dish == null) {
            return false;
        }

        Order order = orderRepository.findById(orderId);

        if(order.getStatus() == OrderStatuses.READY || order.getStatus() == OrderStatuses.PREPARE) {
            return false;
        }

        order.setDifficult(order.getDifficult() + dish.getDifficult());
        order.setPrice(order.getPrice() + dish.getPrice());

        orderRepository.update(order);
        if(orderRepository.findCountDish(orderId, dish.getId()) < 1) {
            orderRepository.saveDishInOrder(orderId, dish.getId());
        }
        orderRepository.updateCounter(orderId, dish.getId(), 1);

        return true;
    }

    public boolean deleteDishInOrder(long orderId, String dishTitle) throws SQLException {
        Dish dish = dishRepository.findByTitle(dishTitle);

        if(dish == null) {
            return false;
        }

        Order order = orderRepository.findById(orderId);

        if(order.getStatus() == OrderStatuses.READY || order.getStatus() == OrderStatuses.PREPARE) {
            return false;
        }

        if(!orderRepository.existDishInOrder(orderId, dish.getId())) {
            return false;
        }

        order.setDifficult(order.getDifficult() - dish.getDifficult());
        order.setPrice(order.getPrice() - dish.getPrice());

        orderRepository.update(order);
        orderRepository.updateCounter(orderId, dish.getId(), -1);
        if(orderRepository.findCountDish(orderId, dish.getId()) < 1) {
            orderRepository.deleteDishInOrder(orderId, dish.getId());
        }

        return true;
    }

    public void createOrder(long userId) throws SQLException {
        orderRepository.save(userId);
    }

    public boolean makeAnOrder(long orderId) throws SQLException {
        Order order = orderRepository.findById(orderId);
        if(order.getDishes().isEmpty()) {
            return false;
        }

        if(order.getStatus() != OrderStatuses.INACTIVE) {
            return false;
        }

        int difficult = order.getDishes().stream()
                        .mapToInt(Dish::getDifficult)
                        .sum();

        int price = order.getDishes().stream()
                        .mapToInt(Dish::getPrice)
                        .sum();

        order.setDifficult(difficult);
        order.setPrice(price);
        order.setStatus(OrderStatuses.ACCEPT);

        orderRepository.update(order);

        return true;
    }

    public AbstractMap.SimpleEntry<Order, Map<Dish, Integer>> infoAboutOrder(long orderId) throws SQLException {
        Map<Dish, Integer> dishCount = new HashMap<>();
        Order order =  orderRepository.findById(orderId);

        if(order == null) {
            return null;
        }


        List<Dish> dishes = order.getDishes();
        for(Dish dish : dishes) {
            dishCount.put(dish, getCountDish(orderId, dish.getId()));
        }


        return new AbstractMap.SimpleEntry<>(order, dishCount);
    }

    public List<AbstractMap.SimpleEntry<Order, Map<Dish, Integer>>> infoAboutOrders(long userId) throws SQLException {
        List<Order> orders = orderRepository.getAllOrdersByUserId(userId);
        List<AbstractMap.SimpleEntry<Order, Map<Dish, Integer>>> result = new ArrayList<>();
        if(orders == null) {
            return null;
        }

        for(Order order : orders) {
            result.add(infoAboutOrder(order.getId()));
        }

        return result;
    }

    private Integer getCountDish(long orderId, long dishId) throws SQLException {
        return orderRepository.findCountDish(orderId, dishId);
    }
}
