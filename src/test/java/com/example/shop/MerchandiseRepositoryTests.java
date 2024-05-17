package com.example.shop;

import com.example.shop.category.CategoryService;
import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseRepository;
import com.example.shop.merchandise.MerchandiseService;
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

    @BeforeEach
    void beforeEach(){
        clearData();
        createSampleData();


    }

    private void clearData(){
        UserRepositoryTests.clearAllData(userRepository, merchandiseRepository);
    }

    private void createSampleData(){
        UserRepositoryTests.createSampleData(userService);
        merchandiseService.createMerchandise("티셔츠1", 15000L, "XL", " ", "image", "MALE", "TOP", "SPRING", userRepository.findByUsername("user1").get());
        merchandiseService.createMerchandise("신발1", 20000L, " ", "260", "image", "MALE", "SHOES", "SPRING", userRepository.findByUsername("user1").get());
    }

    public static void createSampleData(MerchandiseService merchandiseService, UserRepository userRepository){
        merchandiseService.createMerchandise("티셔츠1", 15000L, "XL", " ", "image", "MALE", "TOP", "SPRING", userRepository.findByUsername("user1").get());
        merchandiseService.createMerchandise("신발1", 20000L, " ", "260", "image", "MALE", "SHOES", "SPRING", userRepository.findByUsername("user1").get());
    }

    @Test
    void 셋팅(){
        boolean run = true;

        if(run == false) return;

        IntStream.rangeClosed(3,300).forEach(i -> {
            Merchandise m = Merchandise.builder()
                    .merchandiseName("티셔츠%d".formatted(i))
                    .price(10000L)
                    .image("image")
                    .size("XL")
                    .category(categoryService.findCategory("MALE","TOP", "SPRING"))
                    .seller(userRepository.findByUsername("user1").get())
                    .build();

            merchandiseRepository.save(m);
        });
    }

    @Test
    void 저장(){

        merchandiseService.createMerchandise("티셔츠2", 15000L, "XXL", " ", "image", "MALE", "TOP", "AUTUMN", userRepository.findByUsername("user1").get());
        merchandiseService.createMerchandise("신발2", 20000L, " ", "270", "image", "MALE", "SHOES", "AUTUMN", userRepository.findByUsername("user1").get());

        assertThat(merchandiseRepository.count()).isEqualTo(4L);
    }

}
