package com.Boda.Yamyam.controller;

import com.Boda.Yamyam.domain.User;
import com.Boda.Yamyam.dto.LoginFormDto;
import com.Boda.Yamyam.dto.UserDto;
import com.Boda.Yamyam.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.validation.Valid;

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
