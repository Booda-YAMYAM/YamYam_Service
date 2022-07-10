package com.Boda.Yamyam.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginFormDto {
    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;
}
