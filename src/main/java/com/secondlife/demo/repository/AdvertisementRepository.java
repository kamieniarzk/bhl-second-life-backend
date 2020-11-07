package com.secondlife.demo.repository;

import com.secondlife.demo.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findByOwnerId(Long id);

}
