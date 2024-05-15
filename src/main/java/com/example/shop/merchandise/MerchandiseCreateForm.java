package com.example.shop.merchandise;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MerchandiseCreateForm {

    @Size(max = 20, message = "상품명은 20자 이하여야 합니다.")
    @NotBlank(message = "상품명을 입력해주세요.")
    private String merchandiseName;

    @Min(value = 1, message = "가격은 1이상이어야 합니다.")
    @Max(999999999)
    private long price;

    @NotEmpty(message = "성별을 입력해주세요.")
    private String gender;

    @NotEmpty(message = "타입을 입력해주세요.")
    private String clothType;

    @NotEmpty(message = "계절을 입력해주세요.")
    private String season;

    @NotEmpty(message = "사이즈를 선택해주세요.")
    private String size;

    @NotEmpty(message = "사이즈를 선택해주세요.")
    private String size2;

    @NotEmpty(message = "이미지를 등록해주세요.")
    private String image;
}
