package com.example.shop.review;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.user.SiteUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String review;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Merchandise merchandise;

    public void changeReview(String review){
        this.modifyDate = LocalDateTime.now();
        this.review = review;
    }
}
