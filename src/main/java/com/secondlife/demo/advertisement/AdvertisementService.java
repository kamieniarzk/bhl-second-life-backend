package com.secondlife.demo.advertisement;


import com.secondlife.demo.user.UserProfile;
import com.secondlife.demo.user.UserRepository;
import com.secondlife.demo.util.MathUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    public AdvertisementService(AdvertisementRepository advertisementRepository, UserRepository userRepository) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
    }

    public Advertisement save(Advertisement advertisement) {
        advertisement.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        return advertisementRepository.save(advertisement);
    }

    public List<Advertisement> getAll() {
        return advertisementRepository.findAll();
    }

    public List<Advertisement> getAllByUserId(Long id) {
        return advertisementRepository.findByOwnerId(id);
    }
    public Set<Advertisement> getByUsername(String username) {
        return advertisementRepository.findForUsername(username);
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

    public Set<Advertisement> getAdvertisementsForUser(UserProfile user, double longitude, double latitude) {
        Set<Advertisement> advertisements = new HashSet<>();
        if(user != null) {
            double radius = user.getRadius();
            MathUtil mathUtil = new MathUtil();
            advertisementRepository.findAll().forEach(ad -> {
                if(mathUtil.isWithinRadius(user, longitude, latitude, ad) && !ad.getOwner().equals(user)) {
                    advertisements.add(ad);
                }
            });
        }
        return advertisements;
    }

    public Set<Advertisement> getMatches(String username) {
        UserProfile user = userRepository.getByUsername(username);
        Set<Advertisement> finalSet = new HashSet<>();
        if(user != null) {
            for(Advertisement ad : user.getLikedAdvertisements()) {
                for(Advertisement add : ad.getOwner().getLikedAdvertisements()) {
                    if(add.getOwner().equals(user)) {
                        finalSet.add(add);
                    }
                }
            }
        }
        return finalSet;
    }


    public boolean like(String username, Long id) {
        UserProfile user = userRepository.getByUsername(username);
        Optional<Advertisement> adv = get(id);
        if(user != null && adv.isPresent() && !adv.get().getOwner().getUserName().equals(username)) {
            user.likeAdvert(adv.get());
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
