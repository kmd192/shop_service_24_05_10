package com.example.shop.user;

import com.example.shop.error.SignupEmailDuplicatedException;
import com.example.shop.error.SignupUsernameDuplicatedException;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.review.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    private final MerchandiseService merchandiseService;

    private  final ReviewService reviewService;

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
            userService.createUser(userCreateForm.getUsername(), userCreateForm.getEmail(),
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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/info")
    public String userInfo(UserModifyForm userModifyForm, Principal principal, Model model){
        SiteUser siteUser = userService.getUser(principal.getName());
        model.addAttribute("user", siteUser);
        return "user_info";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String modifyUser(@Valid UserModifyForm userModifyForm, BindingResult bindingResult, Principal principal){
        SiteUser siteUser = userService.getUser(principal.getName());

        if(bindingResult.hasErrors()){
            return "user_info";
        }
        if(!userModifyForm.getPassword1().equals(userModifyForm.getPassword2())){
            bindingResult.rejectValue("password", "passwordIncorrect",
                    "비밀번호가 서로 일치하지 않습니다.");
            return "user_info";
        }

        userService.changeUserBasicInfo(siteUser.getUsername(), userModifyForm.getPassword1(), userModifyForm.getEmail(), userModifyForm.getAddress());
        return "redirect:/";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id, Principal principal){
        SiteUser siteUser = userService.getUser(id);
        if(!siteUser.getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        reviewService.deleteByUserId(id);
        merchandiseService.deleteByUserId(id);
        userService.delete(id);
        return "redirect:/";
    }

}
