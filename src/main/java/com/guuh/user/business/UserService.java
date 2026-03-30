package com.guuh.user.business;

import com.guuh.user.business.converter.UserConverter;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.exceptions.UserNotFoundException;
import com.guuh.user.infraestructure.exceptions.UserAlreadyExistsException;
import com.guuh.user.infraestructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not exists!"));
        return converter.toUserDTO(user);
    }

    public void deleteUserById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not exists!");
        }
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User userSearched = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not exists!"));
        updatePassword(userDTO, userSearched);
        User user = converter.userUpdate(userDTO, userSearched);
        return converter.toUserDTO(userRepository.save(user));

    }

    public void updatePassword(UserDTO userDTO, User user) {
        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
    }


}
