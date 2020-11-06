package com.secondlife.demo.controller;

import com.secondlife.demo.model.Advertisement;
import com.secondlife.demo.model.dto.AdvertisementDTO;
import com.secondlife.demo.service.AdvertisementService;
import com.secondlife.demo.util.AdvertisementDTOMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Validated
@RestController
@RequestMapping("/advertisement")
public class AdvertisementAPI {
    private final AdvertisementDTOMapper mapper;
    private final AdvertisementService advertisementService;

    public AdvertisementAPI(AdvertisementDTOMapper mapper, AdvertisementService advertisementService) {
        this.mapper = mapper;
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public List<AdvertisementDTO> getAll() {
        List<AdvertisementDTO> advertisements = new ArrayList<>();
        advertisementService.getAll().forEach(ad -> {
            advertisements.add(mapper.toDTO(ad));
        });
        return advertisements;
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
    public ResponseEntity save(@Valid @RequestBody AdvertisementDTO advertisementDTO) {
        if(advertisementDTO != null) {
            return ResponseEntity.status(HttpStatus.OK).body(advertisementService.save(mapper.fromDTO(advertisementDTO)));
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

//    @GetMapping("/categories/")
//    public ResponseEntity getCategories(@PathVariable("id") Integer id) {
//
//    }

}
