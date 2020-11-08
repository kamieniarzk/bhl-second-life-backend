package com.secondlife.demo.user;


import com.secondlife.demo.advertisement.AdvertisementRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class UserProfileDTOMapper {

    private final AdvertisementRepository advertisementRepository;

    public UserProfileDTOMapper(AdvertisementRepository advertisementRepository) {
        this.advertisementRepository = advertisementRepository;
    }

    public Set<UserProfileDTO> toDto(Set<UserProfile> users) {
        HashSet<UserProfileDTO> dtos = new HashSet<>();
        users.forEach(user -> {
            dtos.add(toDTO(user));
        });
        return dtos;
    }

    public UserProfile fromDTO(UserProfileDTO userProfileDTO) {
        UserProfile userProfile = new UserProfile();
        if(userProfileDTO == null) {
            return userProfile;
        }
        userProfile.setRadius(userProfileDTO.getRadius());
        userProfile.setUserLastname(userProfileDTO.getUserLastname());
        userProfile.setUserName(userProfileDTO.getUserName());
        userProfile.setLikedAdvertisements(advertisementRepository.findForUsername(userProfileDTO.getUserName()));
        return userProfile;
    }

    public UserProfileDTO toDTO (UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        if(userProfile == null) {
            return userProfileDTO;
        }
        userProfileDTO.setRadius(userProfile.getRadius());
        userProfileDTO.setUserLastname(userProfile.getUserLastname());
        userProfileDTO.setUserName(userProfile.getUserName());
        HashSet<Long> ads = new HashSet<>();
        userProfile.getLikedAdvertisements().forEach(ad -> {
            ads.add(ad.getId());
        });
        userProfileDTO.setAdvertisements(ads);
        return userProfileDTO;
    }

}
