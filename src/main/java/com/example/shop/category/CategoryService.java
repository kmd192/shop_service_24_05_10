package com.example.shop.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findCategory(String gender, String clothType, String season) {
        if (gender == null && clothType != null && season != null) {
            return categoryRepository.findByClothTypeAndSeason(clothType, season);
        } else if (gender == null && clothType == null && season != null) {
            return categoryRepository.findBySeason(season);
        } else if (gender == null && clothType != null && season == null) {
            return categoryRepository.findByClothType(clothType);
        } else if (gender != null && clothType != null && season == null) {
            return categoryRepository.findByGenderAndClothType(gender, clothType);
        } else if (gender != null && clothType == null && season != null) {
            return categoryRepository.findByGenderAndSeason(gender, season);
        } else if (gender != null && clothType == null && season == null) {
            return categoryRepository.findByGender(gender);
        } else if (gender != null && clothType != null && season != null) {
            return categoryRepository.findByGenderAndClothTypeAndSeason(gender, clothType, season);
        } else if (gender == null && clothType == null && season == null) {
            return null;
        }
        return null;
    }

}
