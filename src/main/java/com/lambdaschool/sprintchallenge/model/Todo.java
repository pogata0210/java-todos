package com.lambdaschool.sprintchallenge.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "todo")
public class Todo

{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int todoid;

    @Column(nullable = false)
    private String description;

    private String datestarted;

    private int completed;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)

    private User user;

}