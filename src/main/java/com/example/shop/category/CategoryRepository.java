package com.example.shop.category;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findByGenderAndClothTypeAndSeason(String gender, String clothType, String season);

    List<Category> findByGender(String gender);

    List<Category> findByClothType(String clothType);

    List<Category> findBySeason(String season);

    List<Category> findByGenderAndClothType(String gender, String clothType);

    List<Category> findByGenderAndSeason(String gender, String season);

    List<Category> findByClothTypeAndSeason(String clothType, String season);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE category AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();
}
