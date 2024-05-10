package com.example.shop.review;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "TEXT")
    private String review;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private SiteUser reviewSiteUser;

    @ManyToOne
    private Merchandise merchandise;

    public void changeReview(SiteUser reviewSiteUser, String review){
        this.modifyDate = LocalDateTime.now();
        this.review = review;
    }
}
