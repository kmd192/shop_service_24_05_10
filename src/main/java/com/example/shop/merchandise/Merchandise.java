package com.example.shop.merchandise;

import com.example.shop.cart.Cart;
import com.example.shop.category.Category;
import com.example.shop.review.Review;
import com.example.shop.user.SiteUser;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Merchandise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100)
    private String merchandiseName;

    private long price;

    private String size;

    @ManyToMany
    private Set<SiteUser> like;

    @OneToMany(mappedBy = "merchandise", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category categories;

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private String image;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;
}
