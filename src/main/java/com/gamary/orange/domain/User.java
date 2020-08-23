package com.gamary.orange.domain;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private boolean admin;

    public User(){

    }
    public User(Long id, @NotNull String name, @NotNull boolean admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
    }
}
