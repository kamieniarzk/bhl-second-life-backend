package com.secondlife.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany
    @JoinTable(
            name="user_liked_advertisements",
            joinColumns = @JoinColumn(name="advertisement_id"),
            inverseJoinColumns = @JoinColumn( name="user_id")
    )
    private Set<Advertisement> advertisements;

    public void addAdvert(Advertisement ad) {
        if(advertisements == null) {
            advertisements = new HashSet<>();
        }
        advertisements.add(ad);
    }

}
