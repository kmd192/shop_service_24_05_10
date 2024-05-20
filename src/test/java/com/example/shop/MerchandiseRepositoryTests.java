package com.example.shop;

import com.example.shop.category.CategoryService;
import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseRepository;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.review.ReviewRepository;
import com.example.shop.review.ReviewService;
import com.example.shop.user.UserRepository;
import com.example.shop.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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

    @BeforeEach
    void beforeEach() {
        clearData();
        createSampleData();
    }

    private void clearData() {
        UserRepositoryTests.clearAllData(reviewRepository, userRepository, merchandiseRepository);
    }

    private void createSampleData() {
        UserRepositoryTests.createSampleData(userService);
        merchandiseService.createMerchandise("티셔츠1", 15000L, "XL", " ", "image", "MALE", "TOP", "SPRING", userRepository.findByUsername("user1").get());
        merchandiseService.createMerchandise("신발1", 20000L, " ", "260", "image", "MALE", "SHOES", "SPRING", userRepository.findByUsername("user1").get());
    }

    public static void createSampleData(MerchandiseService merchandiseService, UserRepository userRepository) {
        merchandiseService.createMerchandise("티셔츠1", 15000L, "XL", " ", "image", "MALE", "TOP", "SPRING", userRepository.findByUsername("user1").get());
        merchandiseService.createMerchandise("신발1", 20000L, " ", "260", "image", "MALE", "SHOES", "SPRING", userRepository.findByUsername("user1").get());
    }

    @Test
    void 셋팅() {
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
                                .category(categoryService.findCategory(gender, clothType, season).get(0))
                                .seller(userRepository.findByUsername("user1").get())
                                .build();

                        merchandiseRepository.save(m);

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
