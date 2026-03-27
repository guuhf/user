package com.guuh.user.business;

import com.guuh.user.business.converter.UserConverter;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.exceptions.EmailNotFoundException;
import com.guuh.user.infraestructure.exceptions.UserAlreadyExistsException;
import com.guuh.user.infraestructure.repository.UserRepository;
import com.guuh.user.infraestructure.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter converter;
    private final PasswordEncoder passwordEncoder;

    public UserDTO saveUser(UserDTO userDTO) {
        validateEmailUniqueness(userDTO.getEmail());
        User user = converter.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return converter.toUserDTO(userRepository.save(user));
    }

    public void validateEmailUniqueness(String email) {
        boolean emailExists = userRepository.existsByEmail(email);
        if (emailExists) {
            throw new UserAlreadyExistsException("Email already registered!");
        }
    }

    public UserDTO findUserDTOByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() ->
                new EmailNotFoundException("Email not exists!"));
    }

    public void deleteUserByEmail(String email){
        if (!userRepository.existsByEmail(email)){
            throw new EmailNotFoundException("User not exists!");
        }
        userRepository.deleteByEmail(email);
    }


}
