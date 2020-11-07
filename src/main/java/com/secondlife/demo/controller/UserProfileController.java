package com.secondlife.demo.controller;

import com.secondlife.demo.model.UserProfile;
import com.secondlife.demo.model.dto.UserProfileDTO;
import com.secondlife.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user/profile")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public ResponseEntity<List<UserProfileDTO>> getUserById(){
        return ResponseEntity.ok(userProfileService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getUserById(@PathVariable("id") Long id){
        return ResponseEntity.ok(userProfileService.getUserById(id));
    }

    @PostMapping
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfile userProfile){
        return ResponseEntity.ok(userProfileService.createUser(userProfile));
    }

    @PatchMapping
    public ResponseEntity<UserProfile> updateUser(@RequestBody UserProfile userProfile){
        return ResponseEntity.ok(userProfileService.updateUser(userProfile));
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable("id") Long id){
        userProfileService.deleteUserById(id);
    }


    @DeleteMapping
    public void deleteUser(@RequestBody UserProfile userProfile){
        userProfileService.deleteUser(userProfile);
    }



}
