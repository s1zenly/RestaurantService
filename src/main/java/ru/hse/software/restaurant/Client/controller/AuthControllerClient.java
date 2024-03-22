package ru.hse.software.restaurant.Client.controller;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.controller.AuthController;
import ru.hse.software.restaurant.Server.view.dto.PersonaDTO;

import java.sql.SQLException;
import java.util.AbstractMap;


public class AuthControllerClient {
    private final AuthController authController = new AuthController();
    public AbstractMap.SimpleEntry<String, PersonaDTO> logIn(String email, String password) throws SQLException {
        return authController.logIn(email, password);
    }

    public boolean register(String email, String password) throws SQLException {
        return authController.register(email, password);
    }

    public boolean logout(String email, String password) throws SQLException {
        return authController.logOut(email, password);
    }
}
