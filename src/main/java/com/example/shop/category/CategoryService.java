package com.example.shop.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> findCategory(String gender, String clothType, String season) {
        if (gender == null && clothType != null && season != null) {
            System.out.println(1);
            return categoryRepository.findByClothTypeAndSeason(clothType, season);
        } else if (gender == null && clothType == null && season != null) {
            System.out.println(2);
            return categoryRepository.findBySeason(season);
        } else if (gender == null && clothType != null && season == null) {
            System.out.println(3);
            return categoryRepository.findByClothType(clothType);
        } else if (gender != null && clothType != null && season == null) {
            System.out.println(4);
            return categoryRepository.findByGenderAndClothType(gender, clothType);
        } else if (gender != null && clothType == null && season != null) {
            System.out.println(5);
            return categoryRepository.findByGenderAndSeason(gender, season);
        } else if (gender != null && clothType == null && season == null) {
            System.out.println(6);
            return categoryRepository.findByGender(gender);
        } else if (gender != null && clothType != null && season != null) {
            System.out.println(7);
            return categoryRepository.findByGenderAndClothTypeAndSeason(gender, clothType, season);
        } else if (gender == null && clothType == null && season == null) {
            System.out.println(8);
            return null;
        }
        return null;
    }

}
