package com.example.shop;

import com.example.shop.user.SiteUser;
import com.example.shop.user.SiteUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SiteUserRepositoryTests {

    @Autowired
    private SiteUserRepository userRepository;

    @Test
    void 저장() {
        SiteUser u1 = new SiteUser();
        u1.changeUserBasicInfo("홍길동", "1234", "hone@email.com",
                "경기도 용인시 기흥구 흥덕동");
        userRepository.save(u1);

        System.out.println(userRepository.count() == 1);
    }
}
