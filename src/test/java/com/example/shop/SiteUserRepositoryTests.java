package com.example.shop;

import com.example.shop.user.SiteUser;
import com.example.shop.user.SiteUserRepository;
import com.example.shop.user.SiteUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SiteUserRepositoryTests {

    @Autowired
    private SiteUserRepository siteUserRepository;

    @Autowired
    private SiteUserService siteUserService;

    @Test
    void 저장() {

        siteUserService.create("admin", "admin@test.com", "1234", "경기도 용인시 기흥구");
        siteUserService.create("user1", "user1@test.com", "1234", "경기도 용인시 기흥구");

        SiteUser siteUser = new SiteUser();
        siteUser.changeUserBasicInfo("user2", "1234", "user2@test.com", "경기도 용인시 기흥구");
        siteUserRepository.save(siteUser);

        assertThat(siteUserRepository.count()).isEqualTo(3L);
    }
}
