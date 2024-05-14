package com.example.shop.merchandise;

import com.example.shop.category.Category;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchandiseCreateForm {

    @Size(min = 1, max = 20)
    @NotEmpty(message = "상품명을 입력해주세요.")
    private String merchandiseName;

    @Size(min = 1, max = 10)
    @NotEmpty(message = "가격을 입력해주세요.")
    private long price;

    @NotEmpty(message = "카테고리를 입력해주세요.")
    private Category category;

    @NotEmpty(message = "사이즈를 입력해주세요.")
    private String size;

    @NotEmpty(message = "이미지를 등록해주세요.")
    private String image;
}
