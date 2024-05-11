package com.example.shop;

import com.example.shop.user.SiteUser;
import com.example.shop.user.SiteUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SiteUserRepositoryTests {

    @Autowired
    private SiteUserRepository siteUserRepository;

    @Test
    void 저장() {

        SiteUser u1 = new SiteUser();
        u1.changeUserBasicInfo("홍길동", "1234", "hong@email.com", "경기도 용인시 기흥구");
        siteUserRepository.save(u1);

        assertThat(siteUserRepository.count()).isEqualTo(1L);
    }
}
