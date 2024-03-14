package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.entity.Persona;

public class AdminRepository extends PersonaRepository{
    @Override
    public Admin findByEmailAndPassword(String email, String password) {
        return null; // запрос в БД на поиск админа
    }
}
