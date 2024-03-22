package ru.hse.software.restaurant.Server.view.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentStatusOrder {
    PAID("Paid"),
    UNPAID("Unpaid");

    private final String value;
}
