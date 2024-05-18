package com.example.shop.category;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByGenderAndClothTypeAndSeason(String gender, String clothType, String season);

    List<Category> findByGender(String gender);

    List<Category> findByClothType(String clothType);

    List<Category> findBySeason(String season);

    List<Category> findByGenderAndClothType(String gender, String clothType);

    List<Category> findByGenderAndSeason(String gender, String season);

    List<Category> findByClothTypeAndSeason(String clothType, String season);
}
