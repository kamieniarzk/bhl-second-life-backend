package com.secondlife.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "USER_PROFILE")
public class UserProfile {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "USER_LASTNAME")
    private String userLastname;

    @Column(name = "RADIUS")
    private Double radius;

}
