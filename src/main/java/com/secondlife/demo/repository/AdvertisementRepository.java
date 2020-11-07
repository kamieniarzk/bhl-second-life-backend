package com.secondlife.demo.repository;

import com.secondlife.demo.model.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findByOwnerId(Long id);

    @Query(value = "SELECT p FROM Advertisement p WHERE p.owner.userName=:username")
    Set<Advertisement> findByUsername(String username);

}
