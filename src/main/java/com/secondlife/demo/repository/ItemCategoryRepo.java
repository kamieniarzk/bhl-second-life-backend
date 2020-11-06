package com.secondlife.demo.repository;

import com.secondlife.demo.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCategoryRepo extends JpaRepository<ItemCategory, Long> {
}
