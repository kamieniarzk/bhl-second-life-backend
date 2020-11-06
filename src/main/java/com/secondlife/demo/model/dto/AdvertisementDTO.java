package com.secondlife.demo.model.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Set;

@Data
public class AdvertisementDTO {
    private int id;
    private String title;
    private String imageUrl;
    private String description;
    private double latitude;
    private double longitude;
    private Timestamp cretedDate;
    private long ownerId;
    private Set<Long> itemCategories;
    private long priceCategory;
}
