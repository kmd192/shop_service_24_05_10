package com.example.shop.merchandise;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE merchandise AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();

    Page<Merchandise> findDistinctByMerchandiseNameContainsOrSeller_usernameContainsOrReviewList_reviewContainsOrReviewList_Reviewer_usernameContains
            (String kw1, String kw2, String kw3, String kw4, Pageable pageable);
}
