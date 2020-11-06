package com.secondlife.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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



}
