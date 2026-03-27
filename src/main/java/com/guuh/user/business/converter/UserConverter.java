package com.guuh.user.business.converter;

import com.guuh.user.business.dtos.AddressDTO;
import com.guuh.user.business.dtos.PhoneDTO;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.Address;
import com.guuh.user.infraestructure.entity.Phone;
import com.guuh.user.infraestructure.entity.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {

    public User toUser(UserDTO userDTO){
        return User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .addresses(toAddressList(userDTO.getAddresses()))
                .phones(toPhoneList(userDTO.getPhones()))
                .build();
    }

    public Address toAddress(AddressDTO addresDTO){
        return Address.builder()
                .street(addresDTO.getStreet())
                .number(addresDTO.getNumber())
                .complement(addresDTO.getComplement())
                .neighborhood(addresDTO.getNeighborhood())
                .city(addresDTO.getCity())
                .state(addresDTO.getState())
                .zipCode(addresDTO.getZipCode())
                .country(addresDTO.getCountry())

                .build();
    }

    public List<Address> toAddressList(List<AddressDTO> addressesDTOS){
        List <Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : addressesDTOS){
            addresses.add(toAddress(addressDTO));

        }
        return addresses;
    }

    public Phone toPhones(PhoneDTO phoneDTO){
        return Phone.builder()
                .phoneNumber(phoneDTO.getPhoneNumber())
                .countryCode(phoneDTO.getCountryCode())
                .build();
    }

    public List<Phone> toPhoneList(List<PhoneDTO> phonesDTO){
        List<Phone> phones = new ArrayList<>();
        for (PhoneDTO phoneDTO : phonesDTO){
            phones.add(toPhones(phoneDTO));
        }
        return phones;
    }
}
