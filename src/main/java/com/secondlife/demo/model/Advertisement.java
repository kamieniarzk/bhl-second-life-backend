package com.secondlife.demo.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.Set;

@Data
@Entity
@Table(name = "advertisement")
public class Advertisement {
    @Id
    @Column(name = "advertisement_id")
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @NotEmpty(message = "latitude cannot be empty")
    @Column(name = "latitude")
    private double latitude;
    @NotEmpty(message = "longitude cannot be empty")
    @Column(name = "longitude")
    private double longitude;
    @Column(name = "appearance_date")
    Timestamp createdDate;


    // TODO
    // zrobi sie jak bedzie gotowa BD

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private UserProfile owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "price_category")
    private PriceCategory priceCategory;


    @OneToMany
    @JoinTable(
            name="advertisement_item_category",
            joinColumns = @JoinColumn(name="advertisement_id"),
            inverseJoinColumns = @JoinColumn( name="item_category_id")
    )
    private Set<ItemCategory> itemCategories;

    //TODO zmapować image_url



}
