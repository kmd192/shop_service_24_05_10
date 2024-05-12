package com.example.shop.user;

import com.example.shop.error.SignupEmailDuplicatedException;
import com.example.shop.error.SignupUsernameDuplicatedException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){
        return "login_form";
    }

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm){
        return "signup_form";
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "signup_form";
        }
        if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())){
            bindingResult.rejectValue("password", "passwordIncorrect",
                    "비밀번호가 서로 일치하지 않습니다.");
            return "signup_form";
        }

        try {
            userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(),
                    userCreateForm.getPassword1(), userCreateForm.getAddress());
        } catch (SignupUsernameDuplicatedException e) {
            e.printStackTrace();
            bindingResult.reject("signupUsernameFailed", e.getMessage());
            return "signup_form";
        } catch (SignupEmailDuplicatedException e) {
            e.printStackTrace();
            bindingResult.reject("signupEmailFailed", e.getMessage());
            return "signup_form";
        }

        return "redirect:/";
    }
}
