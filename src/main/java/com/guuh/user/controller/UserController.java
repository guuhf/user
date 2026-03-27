package com.guuh.user.controller;


import com.guuh.user.business.UserService;
import com.guuh.user.business.dtos.UserDTO;
import com.guuh.user.infraestructure.entity.User;
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
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO){
        return ResponseEntity.status(201).body(userService.saveUser(userDTO));
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDTO userDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @GetMapping("/{email}")
    public ResponseEntity<UserDTO> findUserByEmail(@PathVariable String email){
        return ResponseEntity.status(200).body(userService.findUserDTOByEmail(email));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteUserByEmail(@PathVariable String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.status(204).build();
    }
}
