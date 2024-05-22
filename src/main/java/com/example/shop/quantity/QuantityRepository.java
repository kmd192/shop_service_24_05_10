package com.example.shop.quantity;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {

    Quantity findByMerchandiseIdAndCartId(long merchandiseId, long cartId);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE quantity AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();
}
