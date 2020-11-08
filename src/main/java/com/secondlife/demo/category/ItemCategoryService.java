package com.secondlife.demo.category;

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
