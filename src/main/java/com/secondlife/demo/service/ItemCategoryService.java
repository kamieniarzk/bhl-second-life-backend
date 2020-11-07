package com.secondlife.demo.service;

import com.secondlife.demo.model.ItemCategory;
import com.secondlife.demo.repository.ItemCategoryRepo;

import java.util.List;

public class ItemCategoryService {
    private final ItemCategoryRepo itemCategoryRepo;

    public ItemCategoryService(ItemCategoryRepo itemCategoryRepo) {
        this.itemCategoryRepo = itemCategoryRepo;
    }

    public List<ItemCategory> getAll() {
        return itemCategoryRepo.findAll();
    }
}
