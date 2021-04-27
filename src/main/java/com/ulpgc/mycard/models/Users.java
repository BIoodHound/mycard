package com.ulpgc.mycard.models;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String name;

    private String lastName;

    private String email;

    private String password;

}
