package ru.hse.software.restaurant.Server.view.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatuses {
    ACCEPT("Accept"),
    PREPARE("Prepare"),
    READY("Ready");

    private final String value;
}
