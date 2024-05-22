package com.example.shop;

import com.example.shop.cart.Cart;
import com.example.shop.cart.CartRepository;
import com.example.shop.cart.CartService;
import com.example.shop.category.CategoryRepository;
import com.example.shop.category.CategoryService;
import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseRepository;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.quantity.QuantityRepository;
import com.example.shop.quantity.QuantityService;
import com.example.shop.review.ReviewRepository;
import com.example.shop.review.ReviewService;
import com.example.shop.user.SiteUser;
import com.example.shop.user.UserRepository;
import com.example.shop.user.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MerchandiseRepositoryTests {

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private QuantityService quantityService;

    @Autowired
    private QuantityRepository quantityRepository;

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    private void clearData() {
        UserRepositoryTests.clearAllData(quantityRepository, cartRepository, categoryRepository, reviewRepository, userRepository, merchandiseRepository);
    }

    private void createSampleData() {
        CategoryRepositoryTests.createSampleData(categoryRepository);
        UserRepositoryTests.createSampleData(userService);
        merchandiseService.createMerchandise("티셔츠1", 15000L, "XL", " ", "image", "MALE", "TOP", "SPRING", userRepository.findByUsername("user1").get());
        merchandiseService.createMerchandise("신발1", 20000L, " ", "260", "image", "MALE", "SHOES", "SPRING", userRepository.findByUsername("user1").get());
        CartRepositoryTests.createSampleData(quantityService, userService, cartRepository, merchandiseRepository, cartService);
    }

    public static void createSampleData(MerchandiseService merchandiseService, UserRepository userRepository) {
        merchandiseService.createMerchandise("티셔츠1", 15000L, "XL", " ", "image", "MALE", "TOP", "SPRING", userRepository.findByUsername("user1").get());
        merchandiseService.createMerchandise("신발1", 20000L, " ", "260", "image", "MALE", "SHOES", "SPRING", userRepository.findByUsername("user1").get());
    }

    @Transactional
    @Rollback(value = false)
    @Test
    void 셋팅() {
        Cart cart = cartRepository.findById(2L).get();
        SiteUser siteUser = userRepository.findByUsername("user1").get();

        boolean run = true;

        if (run == false) return;

        IntStream.rangeClosed(3, 12).forEach(id -> {
            int mNum = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int g = 0; g < 2; g++) {
                        String gender = "";
                        String clothType = "";
                        String season = "";
                        String mName = "";

                        if (g == 0) {
                            gender = "MALE";
                            mNum += 1;
                        }
                        if (g == 1) {
                            gender = "FEMALE";
                            mNum += 1;
                        }
                        if (j == 0) {
                            clothType = "TOP";
                            mName = "티셔츠";
                            mNum += 1;
                        }
                        if (j == 1) {
                            clothType = "BOTTOM";
                            mName = "반바지";
                            mNum += 1;
                        }
                        if (j == 2) {
                            clothType = "SHOES";
                            mName = "신발";
                            mNum += 1;
                        }
                        if (i == 0) {
                            season = "SPRING";
                            mNum += 1;
                        }
                        if (i == 1) {
                            season = "SUMMER";
                            mNum += 1;
                        }
                        if (i == 2) {
                            season = "AUTUMN";
                            mNum += 1;
                        }
                        if (i == 3) {
                            season = "WINTER";
                            mNum += 1;
                        }

                        Merchandise m = Merchandise.builder()
                                .merchandiseName("%s%d".formatted(mName,mNum))
                                .price(10000L)
                                .size("XL")
                                .image("image")
                                .category(categoryService.findCategory(gender, clothType, season).get(0))
                                .seller(siteUser)
                                .build();

                        merchandiseRepository.save(m);
                        cartService.addMerchandise(cart, m);

                        reviewService.createReview(m, "%d".formatted(mNum), userRepository.findByUsername("user1").get());
                    }
                }
            }
        });
        assertThat(merchandiseRepository.count()).isEqualTo(242);
    }


    @Test
    void 저장(){

        merchandiseService.createMerchandise("티셔츠2", 15000L, "XXL", " ", "image", "MALE", "TOP", "AUTUMN", userRepository.findByUsername("user1").get());
        merchandiseService.createMerchandise("신발2", 20000L, " ", "270", "image", "MALE", "SHOES", "AUTUMN", userRepository.findByUsername("user1").get());

        assertThat(merchandiseRepository.count()).isEqualTo(4L);
    }

}
