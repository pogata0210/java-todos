package com.lambdaschool.sprintchallenge.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User

{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int userid;

    @Column(nullable = false)
    private String username;

    public User()

    {

    }
}