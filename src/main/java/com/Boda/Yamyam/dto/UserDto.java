package com.Boda.Yamyam.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    private String userId;

    private String password;

    private  String name;

    private Long age;

    private String role;
}
