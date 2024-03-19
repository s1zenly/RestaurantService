package ru.hse.software.restaurant.Server.controller;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.service.AuthService;
import ru.hse.software.restaurant.Server.view.dto.AdminDTO;
import ru.hse.software.restaurant.Server.view.dto.PersonaDTO;
import ru.hse.software.restaurant.Server.view.dto.UserDTO;

import java.sql.SQLException;
import java.util.AbstractMap;


public class AuthController {
    public final AuthService authService = new AuthService();

    public AbstractMap.SimpleEntry<String, PersonaDTO> logIn(String email, String password) throws SQLException {
        PersonaDTO personaDTO = authService.verify(email, password);
        return definingTypePersona(personaDTO);
    }

    public boolean register(String email, String password) throws SQLException {
        return authService.register(email, password);
    }

    public boolean logOut(String email, String password) throws SQLException {
        return authService.verify(email, password) != null;
    }

    private AbstractMap.SimpleEntry<String, PersonaDTO> definingTypePersona(PersonaDTO personaDTO) {
        if(personaDTO instanceof UserDTO) {
            return new AbstractMap.SimpleEntry<>("user", personaDTO);
        } else if(personaDTO instanceof AdminDTO) {
            return new AbstractMap.SimpleEntry<>("admin", personaDTO);
        } else {
            return null;
        }
    }
}
