package com.secondlife.demo.advertisement;

import com.secondlife.demo.user.UserProfile;
import com.secondlife.demo.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Validated
@CrossOrigin("*")
@RestController
@RequestMapping("/advertisement")
public class AdvertisementAPI {
    private final AdvertisementDTOMapper mapper;
    private final AdvertisementService advertisementService;
    private final UserRepository userRepository;

    public AdvertisementAPI(AdvertisementDTOMapper mapper,
                            AdvertisementService advertisementService,
                            UserRepository userRepository) {
        this.mapper = mapper;
        this.advertisementService = advertisementService;
        this.userRepository = userRepository;
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
    public ResponseEntity get(@PathVariable("id") Long id) {
        ResponseEntity response;
        Optional<Advertisement> advertisement =  advertisementService.get(id);
        response = advertisement.isPresent() ?
                ResponseEntity.status(HttpStatus.OK).body(mapper.toDTO(advertisement.get())) :
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
    public ResponseEntity delete(@PathVariable("id") Long id) {
        ResponseEntity response = advertisementService.delete(id) ?
                ResponseEntity.status(HttpStatus.OK).body("Deleted successfully") :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Advertisement with given id does not exist.");
        return response;
    }

    @GetMapping("/user/{username}")
    public ResponseEntity getAdvertisementsForUser(@PathVariable("username") String username,
                                                   @RequestParam("longitude") double longitutde,
                                                   @RequestParam("latitude") double latitude) {
        UserProfile user = userRepository.getByUsername(username);
        if(user != null) {
            final Set<AdvertisementDTO> advertisements = new HashSet<>();
            advertisementService.getAdvertisementsForUser(user, longitutde, latitude).forEach(ad -> {
                advertisements.add(mapper.toDTO(ad));
            });
            return ResponseEntity.status(HttpStatus.OK).body(advertisements);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No advertisements to show for this user");
    }

    @PatchMapping("/like/{id}")
    public ResponseEntity likeAdvertisement(@RequestParam("username") String username, @PathVariable("id") Long id) {
        ResponseEntity response = advertisementService.like(username, id) ?
                ResponseEntity.ok().build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong like request.");
        return response;
    }

    @GetMapping("/matches/{username}")
    public ResponseEntity getMatches(@PathVariable("username") String username) {
        Set<AdvertisementDTO> advertisements = mapper.toDTO(advertisementService.getMatches(username));
        ResponseEntity response = advertisements.isEmpty() ?
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No matches for this user") :
                ResponseEntity.status(HttpStatus.OK).body(advertisements);
        return response;
    }


}
