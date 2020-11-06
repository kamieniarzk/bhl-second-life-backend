package com.secondlife.demo.service;

import com.secondlife.demo.model.Advertisement;
import com.secondlife.demo.repository.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public Advertisement save(Advertisement advertisement) {
        return advertisementRepository.save(advertisement);
    }

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public Optional<Advertisement> get(Integer id) {
        return advertisementRepository.findById(id);
    }

    public boolean delete(Integer id) {
        if(advertisementRepository.existsById(id)) {
            advertisementRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
