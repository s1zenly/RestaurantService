package ru.hse.software.restaurant.Server.service;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.entity.Order;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.enums.PaymentStatusOrder;
import ru.hse.software.restaurant.Server.view.repository.OrderRepository;
import ru.hse.software.restaurant.Server.view.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class PaymentService {
    private final UserRepository userRepository = new UserRepository();
    private final OrderRepository orderRepository = new OrderRepository();

    public Integer paidForOrder(long userId, long orderId, int amount) throws SQLException {
        Order order = orderRepository.findById(orderId);
        User user = userRepository.findById(userId);
        if(order.getStatus() != OrderStatuses.READY) {
            return null;
        }

        if(order.getStatusPayment() == PaymentStatusOrder.PAID) {
            return null;
        }

        int remain = amount - order.getPrice();
        if(remain >= 0) {
            user.setMoneyAccount(user.getMoneyAccount() + remain);
        } else {
            user.setMoneyAccount(user.getMoneyAccount() + remain);
        }


        order.setStatusPayment(PaymentStatusOrder.PAID);
        orderRepository.update(order);
        userRepository.update(user);

        return remain;
    }

    public Integer paidAllOrder(long userId, int amount) throws SQLException {
        int remain = amount - getMoneyAccount(userId);
        if(remain >= 0) {
            User user = userRepository.findById(userId);

            user.setMoneyAccount(user.getMoneyAccount() + remain);

            List<Order> orders = orderRepository.getAllOrdersByUserId(userId);

            if(orders != null) {
                orders.forEach(order -> {
                    if(order.getStatusPayment() == PaymentStatusOrder.UNPAID) {
                        order.setStatusPayment(PaymentStatusOrder.PAID);
                        try {
                            orderRepository.update(order);
                        } catch (SQLException e) {
                            throw new RuntimeException();
                        }
                    }
                });

            }

            userRepository.update(user);
        }

        return remain;
    }

    public Integer getMoneyAccount(long userId) throws SQLException {
        User user = (User) userRepository.findById(userId);

        return user.getMoneyAccount();
    }
}
