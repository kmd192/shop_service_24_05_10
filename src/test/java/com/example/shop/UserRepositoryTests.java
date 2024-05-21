package com.example.shop;

import com.example.shop.cart.CartRepository;
import com.example.shop.cart.CartService;
import com.example.shop.category.CategoryRepository;
import com.example.shop.merchandise.MerchandiseRepository;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.review.ReviewRepository;
import com.example.shop.user.UserRepository;
import com.example.shop.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    private MerchandiseRepository merchandiseRepository;

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @BeforeEach
    void beforeEach(){
        clearData();
        createSampleData();
    }

    private void clearData(){
        clearAllData(cartRepository, categoryRepository, reviewRepository, userRepository, merchandiseRepository);
    }

    public static void clearAllData(CartRepository cartRepository, CategoryRepository categoryRepository, ReviewRepository reviewRepository, UserRepository userRepository,
                                    MerchandiseRepository merchandiseRepository){
        reviewRepository.deleteAllInBatch();
        reviewRepository.truncateTable();

        merchandiseRepository.deleteAllInBatch();
        merchandiseRepository.truncateTable();

        categoryRepository.deleteAllInBatch();
        categoryRepository.truncateTable();

        cartRepository.deleteAllInBatch();
        cartRepository.truncateTable();

        userRepository.deleteAllInBatch();
        userRepository.truncateTable();
    }

    private void createSampleData(){
        userService.createUser("admin", "admin@test.com", "1234", "경기도 용인시 기흥구");
        userService.createUser("user1", "user1@test.com", "1234", "경기도 용인시 기흥구");
        userService.addCash("admin", 200000);
        userService.addCash("user1", 200000);

        CategoryRepositoryTests.createSampleData(categoryRepository);
        MerchandiseRepositoryTests.createSampleData(merchandiseService, userRepository);
        CartRepositoryTests.createSampleData(userService, cartRepository, merchandiseRepository, cartService);
    }

    public static void createSampleData(UserService userService){
        userService.createUser("admin", "admin@test.com", "1234", "경기도 용인시 기흥구");
        userService.createUser("user1", "user1@test.com", "1234", "경기도 용인시 기흥구");
        userService.addCash("admin", 200000);
        userService.addCash("user1", 200000);
    }

    @Test
    void 저장() {

        userService.createUser("user2", "user2@test.com", "1234", "경기도 용인시 기흥구");
        userService.createUser("user3", "user3@test.com", "1234", "경기도 용인시 기흥구");

        assertThat(userRepository.count()).isEqualTo(4L);
    }

    @Test
    @DisplayName("변경")
    void changeUserBasicInfo() {

        userService.changeUserBasicInfo("admin", "12345","modify1@test.com", "서울");

        assertTrue(passwordEncoder.matches("12345", userRepository.findByUsername("admin").get().getPassword()));
        assertThat(userRepository.findByUsername("admin").get().getEmail()).isEqualTo("modify1@test.com");
        assertThat(userRepository.findByUsername("admin").get().getAddress()).isEqualTo("서울");
    }
}
