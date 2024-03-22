package ru.hse.software.restaurant.Server.view.repository.abstracts;

import ru.hse.software.restaurant.Server.view.entity.Persona;

import java.sql.SQLException;

public abstract class PersonaRepository {
    public abstract Persona findByEmailAndPassword(String email, String password) throws SQLException;

    public abstract Persona findById(Long id) throws SQLException;
}
