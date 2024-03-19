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

@RequiredArgsConstructor
public class PaymentService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public Integer paidForOrder(long userId, long orderId, int amount) throws SQLException {
        Order order = orderRepository.findById(orderId);
        User user = (User) userRepository.findById(userId);
        if(order.getStatus() != OrderStatuses.READY) {
            return null;
        }

        if(order.getStatusPayment() == PaymentStatusOrder.PAID) {
            return null;
        }

        int remain = amount - order.getDifficult();
        if(remain >= 0) {
            user.setMoneyAccount(user.getMoneyAccount() + remain);
            order.setStatusPayment(PaymentStatusOrder.PAID);
            orderRepository.update(order);
        } else {
            user.setMoneyAccount(user.getMoneyAccount() - order.getDifficult());
        }



        userRepository.update(user);

        return remain;
    }

    public Integer paidAllOrder(long userId, int amount) throws SQLException {
        int remain = amount - getMoneyAccount(userId);
        if(remain >= 0) {
            User user = (User) userRepository.findById(userId);

            user.setMoneyAccount(user.getMoneyAccount() + remain);

            List<Order> orders = orderRepository.getAllOrdersByUserId(userId);

            orders.forEach(order -> {
                if(order.getStatusPayment() == PaymentStatusOrder.UNPAID) {
                    order.setStatusPayment(PaymentStatusOrder.PAID);
                    orderRepository.update(order);
                }
            });

            userRepository.update(user);
        }

        return remain;
    }

    public Integer getMoneyAccount(long userId) throws SQLException {
        User user = (User) userRepository.findById(userId);

        return user.getMoneyAccount();
    }
}
