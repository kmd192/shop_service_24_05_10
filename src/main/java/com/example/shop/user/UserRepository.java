package com.example.shop.user;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository <SiteUser, Long>{

    Optional<SiteUser> findByUsername(String username);

    boolean existsByUsername(String username);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE site_user AUTO_INCREMENT = 1", nativeQuery = true)
    void truncateTable();
}
