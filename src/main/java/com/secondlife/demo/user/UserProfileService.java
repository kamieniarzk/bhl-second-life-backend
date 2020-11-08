package com.secondlife.demo.user;


import com.secondlife.demo.advertisement.AdvertisementService;
import com.secondlife.demo.advertisement.AdvertisementDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        List<UserProfileDTO> userProfileDTOs = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            userProfileDTOs.add(userProfileDTOMapper.toDTO(user));
        });
        return userProfileDTOs;
    }

    public UserProfileDTO getByUsername(String id){
        UserProfile user = userRepository.getByUsername(id);
        if(user == null) {
            return null;
        }
        return userProfileDTOMapper.toDTO(user);
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

    public boolean deleteUserByUsername(String username) {
       UserProfile user = userRepository.getByUsername(username);
       if(user != null) {
           userRepository.deleteById(user.getId());
           return true;
       }
       return false;
    }
}
