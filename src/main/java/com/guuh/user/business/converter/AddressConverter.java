package com.guuh.user.business.converter;

import com.guuh.user.business.dtos.AddressDTO;
import com.guuh.user.infraestructure.entity.Address;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AddressConverter {

    public Address toAddress(AddressDTO addressDTO) {
        return Address.builder()
                .street(addressDTO.getStreet())
                .number(addressDTO.getNumber())
                .complement(addressDTO.getComplement())
                .neighborhood(addressDTO.getNeighborhood())
                .city(addressDTO.getCity())
                .state(addressDTO.getState())
                .zipCode(addressDTO.getZipCode())
                .country(addressDTO.getCountry())

                .build();
    }

    public List<Address> toAddressList(List<AddressDTO> addressesDTOS) {
        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : addressesDTOS) {
            addresses.add(toAddress(addressDTO));

        }
        return addresses;
    }

    public AddressDTO toAddressDTO(Address address) {
        return AddressDTO.builder()
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .country(address.getCountry())

                .build();
    }

    public List<AddressDTO> toAddressDTOList(List<Address> addresses) {
        List<AddressDTO> addressesDTO = new ArrayList<>();

        for (Address address : addresses) {
            addressesDTO.add(toAddressDTO(address));
        }

        return addressesDTO;
    }

    public Address addressUpdate(AddressDTO addressDTO, Address address) {
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
}
