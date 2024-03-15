package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;

import java.util.List;

public class OrderRepository {
    public List<Order> findByUserId(long userId) {
        return null; // запрос к БД на получение всех заказов пользователя
    }

    public Order findById(long orderId) {
        return null; // запрос к БД на получение заказа по Id
    }

    public void saveDishInOrder(long orderId, long dishId) {
        // запрос к БД на добавление блюда в заказ
    }

    public void deleteDishInOrder(long orderId, long dishId) {
        // запрос к БД на удаление блюда из заказа
    }

    public void delete(long orderId) {
        // запрос к БД на удаление заказа
    }

    public void save(long userId) {
        // запрос к БД на создание нового заказа
    }

    public void update(Order order) {
        // запрос к БД на обновление статуса
    }

    public List<Order> getAllOrders() {
        return null; // запрос к БД на поиск всех заказов
    }

    public boolean existDishInOrder(long orderId, long dishId) {
        return true; // запрос к БД на поиск что в конкретном заказе есть конкретное блюдо
    }
}
