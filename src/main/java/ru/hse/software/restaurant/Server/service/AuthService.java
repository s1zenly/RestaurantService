package ru.hse.software.restaurant.Server.service;

import lombok.RequiredArgsConstructor;
import ru.hse.software.restaurant.Server.view.dto.PersonaDTO;
import ru.hse.software.restaurant.Server.view.dto.UserDTO;
import ru.hse.software.restaurant.Server.view.entity.Admin;
import ru.hse.software.restaurant.Server.view.entity.Persona;
import ru.hse.software.restaurant.Server.view.entity.User;
import ru.hse.software.restaurant.Server.view.mapper.mapperWithDependency.PersonaMapper;
import ru.hse.software.restaurant.Server.view.repository.AdminRepository;
import ru.hse.software.restaurant.Server.view.repository.PersonaRepository;
import ru.hse.software.restaurant.Server.view.repository.UserRepository;

@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AdminRepository adminRepository;
    private final PersonaRepository personaRepository;

    public PersonaDTO verify(String email, String password) {
        Persona persona = personaRepository.findByEmailAndPassword(email, password);
        return castingToDTO(persona);
    }

    public boolean register(String email, String password) {
        return userRepository.save(email, password);
    }

    private PersonaDTO castingToDTO(Persona persona) {
        if(persona instanceof User) {
            return PersonaMapper.INSTANCE.toDTO((User) persona);
        } else if(persona instanceof Admin) {
            return PersonaMapper.INSTANCE.toDTO((Admin) persona);
        } else {
            return null;
        }
    }
}
