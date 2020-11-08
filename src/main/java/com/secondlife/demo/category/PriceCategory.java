package com.secondlife.demo.category;

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
    private long id;
    @Column(name = "category_name")
    private String name;
}
