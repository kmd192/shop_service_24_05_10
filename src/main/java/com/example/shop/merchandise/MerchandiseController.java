package com.example.shop.merchandise;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/merchandise")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String merchandiseCreate(MerchandiseCreateForm merchandiseCreateForm){
        return "merchandiseCreate_form";
    }
}
