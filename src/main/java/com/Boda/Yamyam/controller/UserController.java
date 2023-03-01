package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.User;
import com.Boda.Yamyam.dto.UserDto;
import com.Boda.Yamyam.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login/new")
    public ResponseEntity<Void> create(@RequestBody UserDto userDto) {

        User user = User.createUser(userDto, passwordEncoder);
        userService.saveUser(user);

        return ResponseEntity.ok(null);
    }



}
