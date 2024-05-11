package com.example.shop.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class SiteUserController {

    @GetMapping("/login")
    public String login(){
        return "login_form";
    }
}
