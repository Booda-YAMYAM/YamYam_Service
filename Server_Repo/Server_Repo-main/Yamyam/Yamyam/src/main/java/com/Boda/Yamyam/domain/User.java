package com.Boda.Yamyam.domain;

import com.Boda.Yamyam.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User {

    @Id
    private String userId;

    private String password;

    private String name;

    private Long age;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private List<Restaurant> restaurants = new ArrayList<>();

    public static User createUser(UserDto userDto, PasswordEncoder passwordEncoder) {

        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setRole(UserRole.valueOf(userDto.getRole()));
        String password = passwordEncoder.encode(userDto.getPassword());
        user.setPassword(password);
        return user;
    }
}
