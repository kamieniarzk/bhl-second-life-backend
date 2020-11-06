package com.secondlife.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_profile")
public class UserProfile {


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_lastname")
    private String userLastname;

    @Column(name = "radius")
    private Double radius;

}
