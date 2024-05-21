package com.example.shop.cart;

import com.example.shop.user.SiteUser;
import com.example.shop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/cart")
    public String cart(Principal principal, Model model){
        SiteUser siteUser = userService.getUser(principal.getName());
        model.addAttribute("user", siteUser);
        return "cart";
    }

}
