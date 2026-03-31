package com.guuh.user.controller;


import com.guuh.user.business.UserService;
import com.guuh.user.business.dtos.AddressDTO;
import com.guuh.user.business.dtos.PhoneDTO;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.User;
import com.guuh.user.infraestructure.security.JwtUtil;
import jakarta.persistence.Id;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(201).body(userService.saveUser(userDTO));
    }

    @PostMapping ("/me/address")
    public ResponseEntity<AddressDTO> addAddressToUser(@RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(200).body(userService.addAddresToUser(addressDTO));
    }

    @PostMapping("/me/phones")
    public ResponseEntity<PhoneDTO> addPhoneToUser(@RequestBody PhoneDTO phoneDTO){
        return ResponseEntity.status(200).body(userService.addPhoneToUser(phoneDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getLoggedUserData(){
        return ResponseEntity.status(200).body(userService.getLoggedUserData());
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUserById(){
        userService.deleteUserById();
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateUserById(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(200).body(userService.updateUser(userDTO));
    }
}
