package com.guuh.user.business;

import com.guuh.user.business.converter.UserConverter;
import com.guuh.user.business.dtos.AddressDTO;
import com.guuh.user.business.dtos.PhoneDTO;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.Address;
import com.guuh.user.infraestructure.entity.Phone;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.exceptions.*;
import com.guuh.user.infraestructure.repository.AddressRepository;
import com.guuh.user.infraestructure.repository.PhoneRepository;
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
    private final PhoneRepository phoneRepository;
    private final UserConverter converter;
    private final PasswordEncoder passwordEncoder;

    public UserDTO saveUser(UserDTO userDTO) {
        validateEmailUniqueness(userDTO.getEmail());
        User user = converter.toUser(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return converter.toUserDTO(userRepository.save(user));
    }

    public AddressDTO addAddresToUser(AddressDTO addressDTO) {
        Address address = converter.toAddress(addressDTO);
        User user = getLoggedUser();

        user.getAddresses().add(address);
        userRepository.save(user);
        return converter.toAddressDTO(address);

    }

    public PhoneDTO addPhoneToUser(PhoneDTO phoneDTO) {
        Phone phone = converter.toPhone(phoneDTO);
        User user = getLoggedUser();

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

    public void deleteUserById() {
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

    public AddressDTO updateAddress(AddressDTO addressDTO, Long id) {
        User user = getLoggedUser();
        Address addressFound = null;
        for (Address address : user.getAddresses()) {
            if (address.getId().equals(id)) {
                addressFound = address;
                break;
            }
        }

        if (addressFound == null) {
            throw new AddressNotFoundException("Address not found!");
        }

        converter.addressUpdate(addressDTO, addressFound);

        return converter.toAddressDTO(addressRepository.save(addressFound));
    }

    public PhoneDTO updatePhones(PhoneDTO phoneDTO, Long id){
        User user = getLoggedUser();
        Phone phoneFound = null;
        for (Phone phone : user.getPhones()){
            if (phone.getId().equals(id)){
                phoneFound = phone;
                break;
            }
        }
        if (phoneFound == null){
            throw new PhoneNotFoundException("Phone not found!");
        }

        converter.updatePhone(phoneDTO, phoneFound);
        return converter.toPhoneDTO(phoneRepository.save(phoneFound));
    }

}
