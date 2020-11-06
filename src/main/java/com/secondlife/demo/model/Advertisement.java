package com.secondlife.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @NotEmpty(message = "imageUrl cannot be empty")
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "description")
    private String description;
    @NotEmpty(message = "latitude cannot be empty")
    @Column(name = "latitude")
    private long latitude;
    @NotEmpty(message = "longitude cannot be empty")
    @Column(name = "longitude")
    private long longitude;
    @Column(name = "appearance_date")
    Timestamp createdDate;

    // TODO
    // zrobi sie jak bedzie gotowa BD

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private UserProfile owner;


    @OneToMany
    @JoinTable(
            name="advertisement_item_category",
            joinColumns = @JoinColumn(name="advertisement_id"),
            inverseJoinColumns = @JoinColumn( name="item_category_id")
    )
    private Set<PriceCategory> priceCategory;



}
