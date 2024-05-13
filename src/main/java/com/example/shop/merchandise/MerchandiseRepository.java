package com.example.shop.merchandise;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE merchandise AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();
}
