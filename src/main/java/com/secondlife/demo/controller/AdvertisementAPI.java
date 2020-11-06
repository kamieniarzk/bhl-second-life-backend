package com.secondlife.demo.controller;

import com.secondlife.demo.service.AdvertisementService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdvertisementAPI {
    private final AdvertisementService advertisementService;

    public AdvertisementAPI(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }
}
