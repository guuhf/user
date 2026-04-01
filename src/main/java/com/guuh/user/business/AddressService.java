package com.guuh.user.business;

import com.guuh.user.business.converter.AddressConverter;
import com.guuh.user.business.dtos.AddressDTO;
import com.guuh.user.infraestructure.entity.Address;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.exceptions.AddressNotFoundException;
import com.guuh.user.infraestructure.repository.AddressRepository;
import com.guuh.user.infraestructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserService userService;
    private final AddressConverter converter;

    public AddressDTO addAddresToUser(AddressDTO addressDTO) {
        Address address = converter.toAddress(addressDTO);
        User user = userService.getLoggedUser();

        user.getAddresses().add(address);
        userRepository.save(user);
        return converter.toAddressDTO(address);

    }

    public AddressDTO updateAddress(AddressDTO addressDTO, Long id) {
        User user = userService.getLoggedUser();
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
}
