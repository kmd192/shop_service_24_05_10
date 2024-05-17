package com.example.shop.category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByGenderAndClothTypeAndSeason(String gender, String clothType, String season);

    Category findByGender(String gender);

    Category findByClothType(String clothType);

    Category findBySeason(String season);

    Category findByGenderAndClothType(String gender, String clothType);

    Category findByGenderAndSeason(String gender, String season);

    Category findByClothTypeAndSeason(String clothType, String season);
}
