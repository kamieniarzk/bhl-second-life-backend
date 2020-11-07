package com.secondlife.demo.service;

import com.secondlife.demo.model.Advertisement;
import com.secondlife.demo.model.ItemCategory;
import com.secondlife.demo.model.PriceCategory;
import com.secondlife.demo.model.UserProfile;
import com.secondlife.demo.repository.AdvertisementRepository;
import com.secondlife.demo.repository.UserRepository;
import com.secondlife.demo.util.MathUtil;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
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
        return advertisementRepository.findByUsername(username);
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
        double radius = user.getRadius();
        MathUtil mathUtil = new MathUtil();
        Set<Advertisement> advertisements = new HashSet<>();
        Set<ItemCategory> userItemCategories = new HashSet<>();
        Set<PriceCategory> userPriceCategories = new HashSet<>();
        advertisementRepository.findByUsername(user.getUserName()).forEach(ad -> {
            userItemCategories.addAll(ad.getItemCategories());
            userPriceCategories.add(ad.getPriceCategory());
        });
        Set<Advertisement> finalAdvertisements1 = advertisements;
        advertisementRepository.findAll().forEach(ad -> {
            if(mathUtil.isWithinRadius(user, longitude, latitude, ad)){
                finalAdvertisements1.add(ad);
            }
            if(userPriceCategories.contains(ad.getPriceCategory()) && mathUtil.isWithinRadius(user, longitude, latitude, ad)) {
                finalAdvertisements1.add(ad);
            }

            ad.getItemCategories().forEach(cat -> {
                if(userItemCategories.contains(cat) && mathUtil.isWithinRadius(user, longitude, latitude, ad)) {
                    finalAdvertisements1.add(ad);
                }
            });
        });
        List<Advertisement> sortedList = new ArrayList<>(advertisements);
        Collections.sort(sortedList, (a, b) ->
            Integer.compare(a.getItemCategories().size(),  b.getItemCategories().size())
        );
        advertisements = new HashSet<>(sortedList);
        Set<Advertisement> finalAdvertisements = advertisements;
        advertisementRepository.findAll().forEach(ad -> {
            if((ad.getOwner().getUserName() != user.getUserName()) && !finalAdvertisements.contains(ad) && mathUtil.isWithinRadius(user, longitude, latitude, ad)) {
                finalAdvertisements.add(ad);
            }
        });
      return finalAdvertisements;
    }

    public Set<Advertisement> getMatches(String username) {
        UserProfile user = userRepository.getByUsername(username);
        Set<Advertisement> finalSet = new HashSet<>();
        if(user != null) {
            Set<Advertisement> userAdvertisements = user.getAdvertisements();
            userAdvertisements.forEach(usrAd -> {
                usrAd.getOwner().getAdvertisements().forEach(otherAd -> {
                    if(otherAd.getOwner().getUserName().equals(username)) {
                        finalSet.add(otherAd);
                    }
                });
            });
        }
        return finalSet;
    }


}
