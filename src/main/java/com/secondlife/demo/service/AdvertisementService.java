package com.secondlife.demo.service;

import com.secondlife.demo.model.Advertisement;
import com.secondlife.demo.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public Advertisement save(Advertisement advertisement) {
        advertisement.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return advertisementRepository.save(advertisement);
    }

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Optional<Advertisement> get(Long id) {
        return advertisementRepository.findById(id);
    }

    public boolean delete(Long id) {
        if(advertisementRepository.existsById(id)) {
            advertisementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
