package com.secondlife.demo.controller;

import com.secondlife.demo.model.Advertisement;
import com.secondlife.demo.model.UserProfile;
import com.secondlife.demo.model.dto.UserProfileDTO;
import com.secondlife.demo.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin("*")
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

    @GetMapping("/{username}")
    public ResponseEntity getUserById(@PathVariable("username") String username){
        UserProfileDTO user = userProfileService.getByUsername(username);
        if(user != null)
            return ResponseEntity.ok(user);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with given id does not exist");
    }

    @PostMapping("/like")
    public ResponseEntity likeAdvertisement(@RequestParam("username") String username, @RequestParam("advertisementId") Long id) {
        ResponseEntity response = userProfileService.like(username, id) ?
                ResponseEntity.status(HttpStatus.OK).body("OK") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Could not like");
        return response;
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
