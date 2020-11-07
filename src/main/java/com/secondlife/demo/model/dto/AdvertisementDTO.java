package com.secondlife.demo.model.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    @NotNull(message = "latitude cannot be empty.")
    private double latitude;
    @NotNull(message = "longitude cannot be empty.")
    private double longitude;
    private Timestamp cretedDate;
    @Min(value = 1, message = "ownerId cannot be 0 or empty.")
    private long ownerId;
    private Set<Long> itemCategories;
    @NotNull(message = "priceCategory cannot be empty.")
    private long priceCategory;

    public AdvertisementDTO() {
        itemCategories = new HashSet<>();
    }
}
