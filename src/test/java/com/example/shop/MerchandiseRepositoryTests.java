package com.example.shop;

import com.example.shop.merchandise.MerchandiseRepository;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.user.UserRepository;
import com.example.shop.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        merchandiseService.createMerchandise("티셔츠1", 15000L, "105", "");
        merchandiseService.createMerchandise("신발1", 20000L, "275", "");
    }

    public static void createSampleData(MerchandiseService merchandiseService){
        merchandiseService.createMerchandise("티셔츠1", 15000L, "105", "");
        merchandiseService.createMerchandise("신발1", 20000L, "275", "");
    }

    @Test
    void 저장(){

        merchandiseService.createMerchandise("티셔츠2", 15000L, "105", "");
        merchandiseService.createMerchandise("신발2", 20000L, "275", "");

        assertThat(merchandiseRepository.count()).isEqualTo(4L);
    }
}
