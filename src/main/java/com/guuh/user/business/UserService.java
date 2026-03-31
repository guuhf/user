package com.guuh.user.business;

import com.guuh.user.business.converter.UserConverter;
import com.guuh.user.business.dtos.AddressDTO;
import com.guuh.user.business.dtos.PhoneDTO;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.Address;
import com.guuh.user.infraestructure.entity.Phone;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.exceptions.AccessDeniedException;
import com.guuh.user.infraestructure.exceptions.UserNotFoundException;
import com.guuh.user.infraestructure.exceptions.UserAlreadyExistsException;
import com.guuh.user.infraestructure.repository.AddressRepository;
import com.guuh.user.infraestructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserConverter converter;
    private final PasswordEncoder passwordEncoder;

    public UserDTO saveUser(UserDTO userDTO) {
        validateEmailUniqueness(userDTO.getEmail());
        User user = converter.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return converter.toUserDTO(userRepository.save(user));
    }

    public AddressDTO addAddresToUser(AddressDTO addressDTO, Long id) {
        validationUserAccess(id);
        Address address = converter.toAddress(addressDTO);
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));

        user.getAddresses().add(address);
        userRepository.save(user);
        return converter.toAddressDTO(address);

    }

    public PhoneDTO addPhoneToUser(PhoneDTO phoneDTO, Long id) {
        validationUserAccess(id);
        Phone phone = converter.toPhone(phoneDTO);
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not found!"));

        user.getPhones().add(phone);
        userRepository.save(user);
        return converter.toPhoneDTO(phone);
    }

    public void validateEmailUniqueness(String email) {
        boolean emailExists = userRepository.existsByEmail(email);
        if (emailExists) {
            throw new UserAlreadyExistsException("Email already registered!");
        }
    }

    public User getLoggedUser(){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException("User not found!"));
        }

    public void validationUserAccess(Long id){
        User loggedUser = getLoggedUser();
        if (!loggedUser.getId().equals(id)){
            throw new AccessDeniedException("You do not have permission to access this resource");
        }
    }

    public UserDTO findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User not exists!"));
        return converter.toUserDTO(user);
    }

    public void deleteUserById(Long id) {
        validationUserAccess(id);
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not exists!");
        }
        userRepository.deleteById(id);
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        validationUserAccess(id);
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
