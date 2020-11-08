package com.secondlife.demo.category;

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
