package ru.hse.software.restaurant.Server.view.entity;

import lombok.Data;

@Data
public abstract class Persona {
    private Long id;
    private String email;
    private String password;
}
