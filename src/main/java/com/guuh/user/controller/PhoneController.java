package com.guuh.user.controller;

import com.guuh.user.business.PhoneService;
import com.guuh.user.business.dtos.PhoneDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/me/phones")
@RequiredArgsConstructor
public class PhoneController {
    private final PhoneService phoneService;

    @PostMapping()
    public ResponseEntity<PhoneDTO> addPhoneToUser(@RequestBody PhoneDTO phoneDTO) {
        return ResponseEntity.status(201).body(phoneService.addPhoneToUser(phoneDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhoneDTO> phoneUpdate(@RequestBody PhoneDTO phoneDTO,
                                                @PathVariable Long id) {
        return ResponseEntity.ok(phoneService.updatePhones(phoneDTO, id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhoneDTO> getPhoneData(@PathVariable Long id) {
        return ResponseEntity.ok(phoneService.getPhoneData(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhone(@PathVariable Long id) {
        phoneService.deletePhone(id);
        return ResponseEntity.status(204).build();
    }
}
