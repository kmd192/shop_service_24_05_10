package com.example.shop.review;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE review AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();
}
