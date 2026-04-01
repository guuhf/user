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

    public User toUser(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addresses(addressConverter.toAddressList(userDTO.getAddresses()))
                .phones(phoneConverter.toPhoneList(userDTO.getPhones()))
                .build();
    }

    public UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(addressConverter.toAddressDTOList(user.getAddresses()))
                .phones(phoneConverter.toPhoneDTOList(user.getPhones()))
                .build();
    }

    public User userUpdate(UserDTO userDTO, User user){
        if (userDTO.getName() != null){
            user.setName(userDTO.getName());
        }
        if (userDTO.getEmail() != null){
            user.setEmail(userDTO.getEmail());
        }
        return user;
    }

}
