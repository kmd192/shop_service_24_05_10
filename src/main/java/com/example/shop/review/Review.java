package com.example.shop.review;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.user.SiteUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
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

    public Review changeReviewEntity(String review){
        this.modifyDate = LocalDateTime.now();
        this.review = review;

        return this;
    }
}
