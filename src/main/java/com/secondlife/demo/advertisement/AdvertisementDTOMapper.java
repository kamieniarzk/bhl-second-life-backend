package com.secondlife.demo.advertisement;

import com.secondlife.demo.category.ItemCategory;
import com.secondlife.demo.category.PriceCategory;
import com.secondlife.demo.category.ItemCategoryRepo;
import com.secondlife.demo.category.PriceCategoryRepo;
import com.secondlife.demo.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AdvertisementDTOMapper {
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;
    private final PriceCategoryRepo priceCategoryRepo;
    private final ItemCategoryRepo itemCategoryRepo;

    public AdvertisementDTOMapper(AdvertisementRepository advertisementRepository,
                                  UserRepository userRepository,
                                  PriceCategoryRepo priceCategoryRepo,
                                  ItemCategoryRepo itemCategoryRepo) {
        this.advertisementRepository = advertisementRepository;
        this.userRepository = userRepository;
        this.priceCategoryRepo = priceCategoryRepo;
        this.itemCategoryRepo = itemCategoryRepo;
    }

    public Set<AdvertisementDTO> toDTO(Set<Advertisement> advertisements){
        Set<AdvertisementDTO> advertisementDTOS = new HashSet<>();
        advertisements.forEach(advertisement -> {
            advertisementDTOS.add(toDTO(advertisement));
        });

        return advertisementDTOS;
    }

    public AdvertisementDTO toDTO(Advertisement advertisement) {
        AdvertisementDTO advertisementDTO = new AdvertisementDTO();
        if(advertisement == null) {
            return advertisementDTO;
        }
        advertisementDTO.setDescription(advertisement.getDescription());
        advertisementDTO.setTitle(advertisement.getTitle());
        advertisementDTO.setId(advertisement.getId());
        advertisementDTO.setLatitude(advertisement.getLatitude());
        advertisementDTO.setLongitude(advertisement.getLongitude());
        advertisementDTO.setOwner(advertisement.getOwner().getUserName());
        advertisementDTO.setCretedDate(advertisement.getCreatedDate());
        advertisementDTO.setPriceCategory(advertisement.getPriceCategory().getId());
        advertisementDTO.setImageUrls(advertisement.getImageUrls());
        Set<Long> categories = new HashSet<>();
        advertisement.getItemCategories().forEach(itemCategory -> {
            categories.add(itemCategory.getId());
        });
        advertisementDTO.setItemCategories(categories);
        return advertisementDTO;
    }

    public Advertisement fromDTO(AdvertisementDTO advertisementDTO) {
        Advertisement advertisement = new Advertisement();
        if(advertisementDTO == null) {
            return advertisement;
        }
        advertisement.setTitle(advertisementDTO.getTitle());
        advertisement.setDescription(advertisementDTO.getDescription());
        advertisement.setLongitude(advertisementDTO.getLongitude());
        advertisement.setLatitude(advertisementDTO.getLatitude());
        advertisement.setOwner(userRepository.getByUsername(advertisementDTO.getOwner()));
        PriceCategory priceCategory = priceCategoryRepo.getOne(advertisementDTO.getPriceCategory());
        advertisement.setPriceCategory(priceCategory);
        advertisement.setImageUrls(advertisementDTO.getImageUrls());
        Set<ItemCategory> itemCategories = new HashSet<>();
        advertisementDTO.getItemCategories().forEach(cat -> {
            itemCategories.add(itemCategoryRepo.getOne(cat));
        });
        advertisement.setItemCategories(itemCategories);
        return advertisement;
    }
}
