package com.secondlife.demo.service;


import com.secondlife.demo.model.Advertisement;
import com.secondlife.demo.model.UserProfile;
import com.secondlife.demo.model.dto.UserProfileDTO;
import com.secondlife.demo.repository.UserRepository;
import com.secondlife.demo.util.AdvertisementDTOMapper;
import com.secondlife.demo.util.UserProfileDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserProfileService{

    private final UserRepository userRepository;
    private final AdvertisementService advertisementService;
    private final AdvertisementDTOMapper advertisementDTOMapper;
    private final UserProfileDTOMapper userProfileDTOMapper;

    @Autowired
    public UserProfileService(UserRepository userRepository,
                              AdvertisementService advertisementService,
                              AdvertisementDTOMapper advertisementDTOMapper, UserProfileDTOMapper userProfileDTOMapper) {
        this.userRepository = userRepository;
        this.advertisementService = advertisementService;
        this.advertisementDTOMapper = advertisementDTOMapper;
        this.userProfileDTOMapper = userProfileDTOMapper;
    }

    public List<UserProfileDTO> getAll(){
        List<UserProfileDTO> userProfileDTOs = userProfileDTOMapper.toDTO(userRepository.findAll());
        return userProfileDTOs;
    }

    public UserProfileDTO getUserById(Long id){
        UserProfileDTO userProfileDTO = userProfileDTOMapper.toDTO(userRepository.findById(id).get());
        List<Advertisement> advertisements = advertisementService.getAllByUserId(id);
        userProfileDTO.setAdvertisements(advertisementDTOMapper.toDTO(advertisements));
        return userProfileDTO;
    }

    public UserProfile createUser(UserProfile userProfile){
        return userRepository.save(userProfile);
    }

    public UserProfile updateUser(UserProfile userProfile){
        return userRepository.save(userProfile);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }


    public void deleteUser(UserProfile userProfile){
        userRepository.delete(userProfile);
    }

}
