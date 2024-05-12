package com.example.shop;

import com.example.shop.user.UserRepository;
import com.example.shop.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void beforeEach(){
        clearData();
        createSampleData();
    }

    private void clearData(){
        userRepository.deleteAll();
        userRepository.truncateTable();
    }

    private void createSampleData(){
        userService.create("admin", "admin@test.com", "1234", "경기도 용인시 기흥구");
        userService.create("user1", "user1@test.com", "1234", "경기도 용인시 기흥구");
        userService.addCash("admin", 200000);
        userService.addCash("user1", 200000);
    }

    @Test
    void 저장() {

        userService.create("user2", "user2@test.com", "1234", "경기도 용인시 기흥구");
        userService.create("user3", "user3@test.com", "1234", "경기도 용인시 기흥구");

        assertThat(userRepository.count()).isEqualTo(4L);
    }

    @Test
    void 변경() {

        userService.changeUserBasicInfo("admin", "12345","modify1@test.com", "서울");

        assertTrue(passwordEncoder.matches("12345", userRepository.findByUsername("admin").get().getPassword()));
        assertThat(userRepository.findByUsername("admin").get().getEmail()).isEqualTo("modify1@test.com");
        assertThat(userRepository.findByUsername("admin").get().getAddress()).isEqualTo("서울");
    }
}
