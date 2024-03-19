package ru.hse.software.restaurant.Client.controller;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.controller.AuthController;
import ru.hse.software.restaurant.Server.view.dto.PersonaDTO;

import java.sql.SQLException;


public class AuthControllerClient {
    private final AuthController authController = new AuthController();
    public void logIn() throws SQLException {

    }

    public void register() throws SQLException {

    }

    public void logout() throws SQLException {

    }
}
