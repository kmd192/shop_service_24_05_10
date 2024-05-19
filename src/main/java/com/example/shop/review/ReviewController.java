package com.example.shop.review;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.user.SiteUser;
import com.example.shop.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/review")
@Controller
@RequiredArgsConstructor
public class ReviewController {

    @Autowired
    private final ReviewService reviewService;

    @Autowired
    private final MerchandiseService merchandiseService;

    @Autowired
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String reviewCreate(@PathVariable long id, @Valid ReviewForm reviewForm, BindingResult bindingResult,
                               Principal principal, Model model){

        Merchandise merchandise = merchandiseService.getMerchandise(id);

        if(bindingResult.hasErrors()){
            model.addAttribute("merchandise", merchandise);
            return "merchandise_detail";
        }

        SiteUser reviewer = userService.getUser(principal.getName());

        Review review = reviewService.createReview(merchandise, reviewForm.getContent(), reviewer);

        return String.format("redirect:/merchandise/detail/%d#answer_%s", id, review.getId());
    }
}
