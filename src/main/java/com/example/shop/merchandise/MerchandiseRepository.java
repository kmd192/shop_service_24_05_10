package com.example.shop.merchandise;

import com.example.shop.category.Category;
import com.example.shop.user.SiteUser;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MerchandiseRepository extends JpaRepository<Merchandise, Long> {

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE merchandise AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();

    Page<Merchandise> findDistinctByCategoryInAndMerchandiseNameContainsOrSeller_usernameContainsOrReviewList_reviewContainsOrReviewList_Reviewer_usernameContains
            (List<Category> category, String kw1, String kw2, String kw3, String kw4, Pageable pageable);

    Page<Merchandise> findDistinctByCategoryIn
            (List<Category> category, Pageable pageable);

    Page<Merchandise> findDistinctByMerchandiseNameContainsOrSeller_usernameContainsOrReviewList_reviewContainsOrReviewList_Reviewer_usernameContains
            (String kw, String kw1, String kw2, String kw3, Pageable pageable);

    List<Merchandise> findBySeller(SiteUser siteUser);
}
