package com.secondlife.demo.category;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "item_category")
public class ItemCategory {
    @Id
    @Column(name = "category_id")
    private long id;
    @Column(name = "category_name")
    private String name;
}
