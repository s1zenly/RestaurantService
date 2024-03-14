package ru.hse.software.restaurant.Server.view.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccessTypes {
    MANAGER("Manager"),
    WORKER("Worker");

    private final String value;
}
