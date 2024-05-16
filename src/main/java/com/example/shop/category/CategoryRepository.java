package com.example.shop.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByGenderAndClothTypeAndSeason(String gender, String clothType, String season);
}
