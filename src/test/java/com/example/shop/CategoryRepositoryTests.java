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
    void 저장() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int g = 0; g < 2; g++) {
                    String gender = "";
                    String clothType = "";
                    String season = "";

                    if(g==0){ gender = "남성";};
                    if(g==1){ gender = "여성";};
                    if(j==0){ clothType = "상의";};
                    if(j==1){ clothType = "하의";};
                    if(j==2){ clothType = "신발";};
                    if(i==0){ season = "봄";};
                    if(i==1){ season = "여름";};
                    if(i==2){ season = "가을";};
                    if(i==3){ season = "겨울";};

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
