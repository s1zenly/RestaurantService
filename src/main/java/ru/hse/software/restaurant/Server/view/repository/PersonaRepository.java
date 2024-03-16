package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.entity.Persona;

public abstract class PersonaRepository {
    public abstract Persona findByEmailAndPassword(String email, String password);

    public Persona findById(Long id) {
        return null; // запрос к БД на поиск по ID
    }
}
