package ru.hse.software.restaurant.Server.view.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentStatusOrder {
    PAID("Paid"),
    UNPAID("Unpaid");

    private final String value;
}
