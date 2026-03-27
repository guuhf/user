package com.guuh.user.business;

import com.guuh.user.business.converter.UserConverter;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.exceptions.UserAlreadyExistsException;
import com.guuh.user.infraestructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter converter;

    public UserDTO saveUser(UserDTO userDTO){
       User user  = converter.toUser(userDTO);
       validateEmailUniqueness(user.getEmail());
       return converter.toUserDTO(userRepository.save(user));
    }

    public void validateEmailUniqueness(String email){
        boolean emailExists = userRepository.existsByEmail(email);
        if (emailExists){
            throw new UserAlreadyExistsException("Email already registered!");
        }
    }
}
