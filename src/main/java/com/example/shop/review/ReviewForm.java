package com.example.shop.review;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewForm {

    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;
}
