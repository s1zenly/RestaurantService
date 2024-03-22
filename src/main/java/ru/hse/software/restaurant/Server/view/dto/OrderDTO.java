package ru.hse.software.restaurant.Server.view.dto;

import lombok.Data;
import ru.hse.software.restaurant.Server.view.enums.OrderStatuses;
import ru.hse.software.restaurant.Server.view.enums.PaymentStatusOrder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderDTO {
    private Long id;
    private Timestamp date;
    private Long userId;
    private Integer difficult;
    private Integer price;
    private OrderStatuses status;
    private PaymentStatusOrder statusPayment;
    private List<DishDTO> dishes;

    @Override
    public String toString() {
        return """
                id: %s
                date: %s
                difficult: %s
                price: %s
                status: %s
                payment_status: %s
                dishes:""".formatted(id, date.toString(), difficult, price, status.toString(), statusPayment.toString());
    }

}
