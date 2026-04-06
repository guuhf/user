package com.guuh.user.controller;

import com.guuh.user.business.AddressService;
import com.guuh.user.business.dtos.AddressDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/me/addresses")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @PostMapping()
    public ResponseEntity<AddressDTO> addAddressToUser(@RequestBody AddressDTO addressDTO) {
        return ResponseEntity.status(201).body(addressService.addAddresToUser(addressDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> addressUpdate(@RequestBody AddressDTO addressDTO,
                                                    @PathVariable Long id) {
        return ResponseEntity.ok(addressService.updateAddress(addressDTO, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressData(@PathVariable Long id) {
        return ResponseEntity.ok(addressService.getAddressData(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.status(204).build();
    }
}
