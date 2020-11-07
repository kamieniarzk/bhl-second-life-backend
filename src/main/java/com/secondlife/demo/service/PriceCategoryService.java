package com.secondlife.demo.service;

import com.secondlife.demo.model.PriceCategory;
import com.secondlife.demo.repository.PriceCategoryRepo;

import java.util.List;

public class PriceCategoryService {
    private final PriceCategoryRepo priceCategoryRepo;

    public PriceCategoryService(PriceCategoryRepo priceCategoryRepo) {
        this.priceCategoryRepo = priceCategoryRepo;
    }

    public List<PriceCategory> getAll() {
        return priceCategoryRepo.findAll();
    }
}
