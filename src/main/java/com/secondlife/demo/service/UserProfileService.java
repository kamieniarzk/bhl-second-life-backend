package com.secondlife.demo.service;


import com.secondlife.demo.model.UserProfile;
import com.secondlife.demo.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserProfileService{

    private UserRepository userRepository;

    @Autowired
    public UserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserProfile> getAll(){
        return userRepository.findAll();
    }

    public UserProfile getUserById(Long id){
        return userRepository.findById(id).get();
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
