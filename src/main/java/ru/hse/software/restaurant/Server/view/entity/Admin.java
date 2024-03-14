package ru.hse.software.restaurant.Server.view.entity;

import lombok.Data;
import ru.hse.software.restaurant.Server.view.enums.AccessTypes;

@Data
public class Admin extends Persona{
    private AccessTypes accessType;
}
