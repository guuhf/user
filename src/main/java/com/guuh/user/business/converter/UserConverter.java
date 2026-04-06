package com.guuh.user.business.converter;

import com.guuh.user.business.dtos.AddressDTO;
import com.guuh.user.business.dtos.PhoneDTO;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.Address;
import com.guuh.user.infraestructure.entity.Phone;
import com.guuh.user.infraestructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final AddressConverter addressConverter;
    private final PhoneConverter phoneConverter;

    public User toUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .build();

        List<Address> addresses = addressConverter.toAddressList(userDTO.getAddresses());
        if (addresses != null) {
            addresses.forEach(address -> address.setUser(user));
            user.setAddresses(addresses);
        }

        List<Phone> phones = phoneConverter.toPhoneList(userDTO.getPhones());
        if (phones != null) {
            phones.forEach(phone -> phone.setUser(user));
            user.setPhones(phones);
        }

        return user;
    }

    public UserDTO toUserDTO(User user) {
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(addressConverter.toAddressDTOList(user.getAddresses()))
                .phones(phoneConverter.toPhoneDTOList(user.getPhones()))
                .build();
    }

    public User userUpdate(UserDTO userDTO, User user) {
        if (userDTO.getName() != null) {
            user.setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }
        return user;
    }

}
