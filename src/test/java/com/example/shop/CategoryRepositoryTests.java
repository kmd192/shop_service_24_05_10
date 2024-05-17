package com.example.shop;

import com.example.shop.category.Category;
import com.example.shop.category.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CategoryRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void beforeEach(){
        clearData();
    }

    private void clearData(){
        categoryRepository.deleteAllInBatch();
    }

    @Test
    void 셋팅() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int g = 0; g < 2; g++) {
                    String gender = "";
                    String clothType = "";
                    String season = "";

                    if(g==0){ gender = "MALE";};
                    if(g==1){ gender = "FEMALE";};
                    if(j==0){ clothType = "TOP";};
                    if(j==1){ clothType = "BOTTOM";};
                    if(j==2){ clothType = "SHOES";};
                    if(i==0){ season = "SPRING";};
                    if(i==1){ season = "SUMMER";};
                    if(i==2){ season = "AUTUMN";};
                    if(i==3){ season = "WINTER";};

                    Category category = Category.builder()
                            .gender(gender)
                            .clothType(clothType)
                            .season(season)
                            .build();

                    categoryRepository.save(category);
                }
            }
        }
        assertThat(categoryRepository.count()).isEqualTo(24L);
    }

}
