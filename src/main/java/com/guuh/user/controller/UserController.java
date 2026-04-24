package com.guuh.user.controller;


import com.guuh.user.business.UserService;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.security.JwtUtil;
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

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(201).body(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO) {
        return userService.userAuth(userDTO);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getLoggedUserData() {
        return ResponseEntity.status(200).body(userService.getLoggedUserData());
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/me")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.status(200).body(userService.updateUser(userDTO));
    }
}
