package com.example.shop.merchandise;

import com.example.shop.user.SiteUser;
import com.example.shop.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/merchandise")
public class MerchandiseController {

    private static final Logger log = LoggerFactory.getLogger(MerchandiseController.class);
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
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(error -> error.getDefaultMessage())
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }

        SiteUser seller = userService.getUser(principal.getName());

        merchandiseService.createMerchandise(merchandiseCreateForm.getMerchandiseName(), merchandiseCreateForm.getPrice(),
                merchandiseCreateForm.getSize(), merchandiseCreateForm.getSize2(), merchandiseCreateForm.getImage(),
                merchandiseCreateForm.getGender(), merchandiseCreateForm.getClothType(), merchandiseCreateForm.getSeason(),
                seller);

        return ResponseEntity.ok("상품이 성공적으로 등록되었습니다.");
    }

    @GetMapping("/detail/{id}")
    public String merchandiseDetail(Model model, @PathVariable long id){
        Merchandise merchandise = merchandiseService.getMerchandise(id);
        model.addAttribute("merchandise", merchandise);
        return "merchandise_detail";
    }

}
