package com.ulpgc.mycard.dto;

import lombok.Data;

@Data
public class UserDto {
    private String username;

    private String name;

    private String lastName;

    private String email;

    private String password;
}
