package com.secondlife.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
public class AdvertisementDTO {
    private long id;
    @NotEmpty(message = "Title cannot be empty.")
    private String title;
    @NotEmpty(message = "imageUrls cannot be empty.")
    private Set<String> imageUrls;
    private String description;
    @NotEmpty(message = "latitude cannot be empty.")
    private double latitude;
    @NotEmpty(message = "longitude cannot be empty.")
    private double longitude;
    private Timestamp cretedDate;
    @NotEmpty(message = "ownerId cannot be empty.")
    private long ownerId;
    private Set<Long> itemCategories;
    @NotEmpty(message = "priceCategory cannot be empty.")
    private long priceCategory;

    public AdvertisementDTO() {
        itemCategories = new HashSet<>();
    }
}
