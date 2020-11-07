package com.secondlife.demo.repository;

import com.secondlife.demo.model.PriceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceCategoryRepo extends JpaRepository<PriceCategory, Long> {
}
