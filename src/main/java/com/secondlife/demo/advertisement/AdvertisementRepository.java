package com.secondlife.demo.advertisement;

import com.secondlife.demo.advertisement.Advertisement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findByOwnerId(Long id);

    @Query(value = "SELECT p FROM Advertisement p WHERE p.owner.userName=:username")
    Set<Advertisement> findForUsername(String username);

}
