package com.example.shop.merchandise;

import com.example.shop.user.SiteUser;
import com.example.shop.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/merchandise")
public class MerchandiseController {

    private final MerchandiseService merchandiseService;

    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String merchandiseCreate(MerchandiseCreateForm merchandiseCreateForm){
        return "merchandiseCreate_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<?> merchandiseCreate(@Valid @RequestBody MerchandiseCreateForm merchandiseCreateForm, Principal principal, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body("입력값이 올바르지 않습니다.");
        }

        SiteUser seller = userService.getUser(principal.getName());

        try {
            merchandiseService.createMerchandise(merchandiseCreateForm.getMerchandiseName(), merchandiseCreateForm.getPrice(),
                    merchandiseCreateForm.getSize(), merchandiseCreateForm.getImage(), seller);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("상품 생성 중 오류가 발생했습니다.");
        }
            return ResponseEntity.ok("상품이 성공적으로 등록되었습니다.");
    }

}
