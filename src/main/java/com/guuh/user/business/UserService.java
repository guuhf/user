package com.guuh.user.business;

import com.guuh.user.business.converter.UserConverter;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.exceptions.UserAlreadyExistsException;
import com.guuh.user.infraestructure.exceptions.UserNotFoundException;
import com.guuh.user.infraestructure.repository.AddressRepository;
import com.guuh.user.infraestructure.repository.PhoneRepository;
import com.guuh.user.infraestructure.repository.UserRepository;
import com.guuh.user.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserConverter converter;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public UserDTO saveUser(UserDTO userDTO) {
        validateEmailUniqueness(userDTO.getEmail());
        User user = converter.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return converter.toUserDTO(userRepository.save(user));
    }

    public String userAuth(UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    public void validateEmailUniqueness(String email) {
        boolean emailExists = userRepository.existsByEmail(email);
        if (emailExists) {
            throw new UserAlreadyExistsException("Email already registered!");
        }
    }

    public User getLoggedUser() {
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
    }

    public UserDTO getLoggedUserData() {
        User user = getLoggedUser();
        return converter.toUserDTO(user);
    }

    public void deleteUser() {
        User user = getLoggedUser();
        userRepository.delete(user);
    }

    public UserDTO updateUser(UserDTO userDTO) {
        User userSearched = getLoggedUser();
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
