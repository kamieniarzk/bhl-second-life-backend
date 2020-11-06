package com.secondlife.demo.controller;

import com.secondlife.demo.model.Advertisement;
import com.secondlife.demo.service.AdvertisementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/advertisement")
public class AdvertisementAPI {
    private final AdvertisementService advertisementService;

    public AdvertisementAPI(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public List<Advertisement> getAll() {
        return advertisementService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Integer id) {
        ResponseEntity response;
        var advertisement =  advertisementService.get(id);
        response = advertisement.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(advertisement.get()) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Advertisement with given id does not exist.");
        return response;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Advertisement advertisement) {
        if(advertisement != null) {
            return ResponseEntity.status(HttpStatus.OK).body(advertisementService.save(advertisement));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Advertisement was null!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        ResponseEntity response = advertisementService.delete(id) ?
                ResponseEntity.status(HttpStatus.OK).body("Deleted successfully") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Advertisement with given id does not exist.");
        return response;
    }

}
