package com.secondlife.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "price_category")
public class PriceCategory {
    @Id
    @Column(name = "category_id")
    private int id;
    @Column(name = "category_name")
    private String name;
}
