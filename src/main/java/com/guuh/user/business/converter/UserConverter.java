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

    public Phone toPhone(PhoneDTO phoneDTO){
        return Phone.builder()
                .phoneNumber(phoneDTO.getPhoneNumber())
                .countryCode(phoneDTO.getCountryCode())
                .build();
    }

    public List<Phone> toPhoneList(List<PhoneDTO> phonesDTO){
        List<Phone> phones = new ArrayList<>();
        for (PhoneDTO phoneDTO : phonesDTO){
            phones.add(toPhone(phoneDTO));
        }
        return phones;
    }

    public UserDTO toUserDTO(User user){
        return UserDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .addresses(toAddressDTOList(user.getAddresses()))
                .phones(toPhoneDTOList(user.getPhones()))
                .build();
    }

    public AddressDTO toAddressDTO(Address addres){
        return AddressDTO.builder()
                .street(addres.getStreet())
                .number(addres.getNumber())
                .complement(addres.getComplement())
                .neighborhood(addres.getNeighborhood())
                .city(addres.getCity())
                .state(addres.getState())
                .zipCode(addres.getZipCode())
                .country(addres.getCountry())

                .build();
    }

    public List<AddressDTO> toAddressDTOList(List<Address> addresses) {
        List<AddressDTO> addressesDTO = new ArrayList<>();

        for (Address address : addresses) {
            addressesDTO.add(toAddressDTO(address));
        }

        return addressesDTO;
    }


    public PhoneDTO toPhoneDTO(Phone phone){
        return PhoneDTO.builder()
                .phoneNumber(phone.getPhoneNumber())
                .countryCode(phone.getCountryCode())
                .build();
    }

    public List<PhoneDTO> toPhoneDTOList(List<Phone> phones){
        List<PhoneDTO> phonesDTO = new ArrayList<>();
        for (Phone phone : phones){
            phonesDTO.add(toPhoneDTO(phone));
        }
        return phonesDTO;
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

    public Address addressUpdate(AddressDTO addressDTO, Address address){
        if (addressDTO.getStreet() != null) {
            address.setStreet(addressDTO.getStreet());
        }
        if (addressDTO.getNumber() != null) {
            address.setNumber(addressDTO.getNumber());
        }
        if (addressDTO.getComplement() != null) {
            address.setComplement(addressDTO.getComplement());
        }
        if (addressDTO.getNeighborhood() != null) {
            address.setNeighborhood(addressDTO.getNeighborhood());
        }
        if (addressDTO.getCity() != null) {
            address.setCity(addressDTO.getCity());
        }
        if (addressDTO.getState() != null) {
            address.setState(addressDTO.getState());
        }
        if (addressDTO.getZipCode() != null) {
            address.setZipCode(addressDTO.getZipCode());
        }
        if (addressDTO.getCountry() != null) {
            address.setCountry(addressDTO.getCountry());
        }

        return address;
    }

    public Phone updatePhone(PhoneDTO phoneDTO, Phone phone){
        if (phoneDTO.getPhoneNumber() != null){
            phone.setPhoneNumber(phoneDTO.getPhoneNumber());
        }
        if (phoneDTO.getCountryCode() != null){
            phone.setCountryCode(phoneDTO.getCountryCode());
        }

        return phone;
    }

}
