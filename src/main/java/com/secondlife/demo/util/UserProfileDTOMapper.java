package com.secondlife.demo.util;

import com.secondlife.demo.model.Advertisement;
import com.secondlife.demo.model.UserProfile;
import com.secondlife.demo.model.dto.UserProfileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class UserProfileDTOMapper {

    private final AdvertisementDTOMapper advertisementDTOMapper;

    @Autowired
    public UserProfileDTOMapper(AdvertisementDTOMapper advertisementDTOMapper) {
        this.advertisementDTOMapper = advertisementDTOMapper;
    }

    public List<UserProfileDTO> toDTO (List<UserProfile> userProfiles) {
        List<UserProfileDTO> userProfileDTOs = new ArrayList<>();

        userProfiles.forEach(userProfile -> {
            userProfileDTOs.add(toDTO(userProfile));
        });

        return userProfileDTOs;
    }

    public UserProfileDTO toDTO (UserProfile userProfile) {
        UserProfileDTO userProfileDTO = new UserProfileDTO();
        userProfileDTO.setRadius(userProfile.getRadius());
        userProfileDTO.setUserLastname(userProfile.getUserLastname());
        userProfileDTO.setUserName(userProfile.getUserName());
        HashSet<Long> ads = new HashSet<>();
        userProfile.getAdvertisements().forEach(ad -> {
            ads.add(ad.getId());
        });
        userProfileDTO.setAdvertisements(ads);
        return userProfileDTO;
    }

}
