package com.example.shop.merchandise;

import com.example.shop.category.Category;
import com.example.shop.review.Review;
import com.example.shop.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
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

    @OneToMany
    private List<Review> reviewList = new ArrayList<>();

    @ManyToMany
    private List<Category> categories;

    @ManyToOne
    private SiteUser registSiteUser;

    @ManyToOne
    private SiteUser cartSiteUser;

    public void changeMerchandiseInfo(SiteUser registSiteUser, String merchandiseName,
                                      long price, String size, List<Category> categories){
        this.merchandiseName = merchandiseName;
        this.price = price;
        this.categories = categories;
    }
}
