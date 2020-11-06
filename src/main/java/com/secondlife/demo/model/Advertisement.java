package com.secondlife.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @Column(name = "advertisement_id")
    private int id;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "description")
    private String description;
    @Column(name = "latitude")
    private long latitude;
    @Column(name = "longitude")
    private long longitude;
    @Column(name = "appearance_date")
    Timestamp createdDate;

    // TODO
    // zrobi sie jak bedzie gotowa BD

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "owner_id")
//    private UserProfile owner;

    @OneToMany
    @JoinTable(
            name="advertisement_item_category",
            joinColumns = @JoinColumn(name="advertisement_id"),
            inverseJoinColumns = @JoinColumn( name="item_category")
    )
    private Set<PriceCategory> priceCategory;



}
