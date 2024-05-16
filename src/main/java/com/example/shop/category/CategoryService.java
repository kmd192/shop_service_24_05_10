package com.example.shop.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category createCategory(String gender, String clothType, String season) {
        return categoryRepository.findByGenderAndClothTypeAndSeason(gender, clothType, season);
    }
}
