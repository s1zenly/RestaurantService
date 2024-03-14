package ru.hse.software.restaurant.Server.view.repository;

import ru.hse.software.restaurant.Server.view.entity.Persona;
import ru.hse.software.restaurant.Server.view.entity.User;


public class UserRepository extends PersonaRepository{
    @Override
    public User findByEmailAndPassword(String email, String password) {
        return null; // запрос в БД на поиска User
    }

    public boolean save(String email, String password) {
        if(findByEmailAndPassword(email, password) != null) {
            return false;
        }

        // запрос к БД на сохранение пользователя
        return true;
    }
}
