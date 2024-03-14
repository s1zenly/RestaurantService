package ru.hse.software.restaurant.Server.view.dto;

import lombok.Data;
import ru.hse.software.restaurant.Server.view.enums.AccessTypes;

@Data
public class AdminDTO extends PersonaDTO {
    private AccessTypes accessType;
}
