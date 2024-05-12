package com.example.shop;

import com.example.shop.user.UserRepository;
import com.example.shop.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    void 저장() {

        userService.create("admin", "admin@test.com", "1234", "경기도 용인시 기흥구");
        userService.create("user1", "user1@test.com", "1234", "경기도 용인시 기흥구");

        assertThat(userRepository.count()).isEqualTo(2L);
    }
}
