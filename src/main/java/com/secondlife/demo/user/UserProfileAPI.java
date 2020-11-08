package com.secondlife.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserProfileAPI {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileAPI(UserProfileService userProfileService) {
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("User with given username does not exist");
    }

    @PostMapping
    public ResponseEntity<UserProfile> createUser(@RequestBody UserProfile userProfile){
        return ResponseEntity.ok(userProfileService.createUser(userProfile));
    }

    @PatchMapping
    public ResponseEntity<UserProfile> updateUser(@RequestBody UserProfile userProfile){
        return ResponseEntity.ok(userProfileService.updateUser(userProfile));
    }

    @DeleteMapping("/{username}")
    public void deleteUserById(@PathVariable("username") String username){
        ResponseEntity response = userProfileService.deleteUserByUsername(username) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with given name does not exist.");

    }

    @DeleteMapping
    public void deleteUser(@RequestBody UserProfile userProfile){
        userProfileService.deleteUser(userProfile);
    }





}
