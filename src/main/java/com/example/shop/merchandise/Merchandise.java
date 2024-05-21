package com.example.shop.merchandise;

import com.example.shop.cart.Cart;
import com.example.shop.category.Category;
import com.example.shop.quantity.Quantity;
import com.example.shop.review.Review;
import com.example.shop.user.SiteUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToMany(mappedBy = "merchandise")
    private final List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private SiteUser seller;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    private String image;

    @OneToMany(mappedBy = "merchandise")
    private final List<Quantity> quantity = new ArrayList<>();

    Merchandise changeMerchandiseInfoEntity(String merchandiseName, long price,
                          String size, String image){
        this.merchandiseName = merchandiseName;
        this.price = price;
        this.size = size;
        this.image = image;

        return this;
    }
}
