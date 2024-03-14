package ru.hse.software.restaurant.Server.view.dto;

import lombok.Data;

@Data
public abstract class PersonaDTO {
    private Long id;
    private String email;
    private String password;
}
