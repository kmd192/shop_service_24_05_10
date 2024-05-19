package com.example.shop.review;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.user.SiteUser;
import com.example.shop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewService {

    @Autowired
    private final ReviewRepository reviewRepository;

    @Autowired
    private final MerchandiseService merchandiseService;

    @Autowired
    private final UserService userService;

    public Review createReview(Merchandise merchandise, String content, SiteUser reviewer) {
        Review review = Review.builder()
                .review(content)
                .createDate(LocalDateTime.now())
                .reviewer(reviewer)
                .merchandise(merchandise)
                .build();

        reviewRepository.save(review);

        return review;
    }
}
