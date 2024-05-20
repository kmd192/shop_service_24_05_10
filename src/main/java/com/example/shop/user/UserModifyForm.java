package com.example.shop.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserModifyForm {

    @Size(min = 4, max = 20)
    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password1;

    @NotEmpty(message = "비밀번호 확인을 입력해주세요.")
    private String password2;

    @Email(message = "올바른 이메일 형식으로 입력해주세요.")
    @NotEmpty(message = "이메일은 필수항목입니다.")
    private String email;

    private String address;
}

