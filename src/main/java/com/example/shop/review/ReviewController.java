package com.example.shop.review;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.user.SiteUser;
import com.example.shop.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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

        return String.format("redirect:/merchandise/detail/%d#review_%s", id, review.getId());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String reviewModify(ReviewForm reviewForm, @PathVariable long id, Principal principal) {

        Review review = reviewService.getReview(id);

        if (!review.getReviewer().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        reviewForm.setContent(review.getReview());

        return "review_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String reviewModify2(@Valid ReviewForm reviewForm, @PathVariable long id, Principal principal, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return "review_form";
        }

        Review review = reviewService.getReview(id);

        if(!review.getReviewer().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        reviewService.changeReview(id, reviewForm.getContent());

        return String.format("redirect:/merchandise/detail/%d#review_%s", review.getMerchandise().getId(), review.getId());
    }

}
