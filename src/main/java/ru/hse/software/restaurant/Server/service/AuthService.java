package ru.hse.software.restaurant.Server.service;

import ru.hse.software.restaurant.Server.view.dto.PersonaDTO;
import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.entity.Persona;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.PersonaMapper;
import ru.hse.software.restaurant.Server.view.repository.AdminRepository;
import ru.hse.software.restaurant.Server.view.repository.UserRepository;

import java.sql.SQLException;

public class AuthService {

    private final UserRepository userRepository = new UserRepository();
    private final AdminRepository adminRepository = new AdminRepository();

    public PersonaDTO verify(String email, String password) throws SQLException {
        Persona user = userRepository.findByEmailAndPassword(email.toLowerCase(), password.toLowerCase());
        Persona admin = adminRepository.findByEmailAndPassword(email.toLowerCase(), password.toLowerCase());

        if(user != null) {
            return PersonaMapper.INSTANCE.toDTO((User) user);
        } else if(admin != null) {
            return PersonaMapper.INSTANCE.toDTO((Admin) admin);
        }

        return null;
    }

    public boolean register(String email, String password) throws SQLException {
        return userRepository.save(email.toLowerCase(), password.toLowerCase());
    }
}
