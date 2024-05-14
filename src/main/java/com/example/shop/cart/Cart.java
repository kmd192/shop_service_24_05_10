package com.example.shop.cart;

import com.example.shop.user.SiteUser;
import jakarta.persistence.*;

@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String merchandiseList;

    @OneToOne(mappedBy = "cart")
    private SiteUser cartUser;

    public void changeCart(String merchandiseList){
        this.merchandiseList = merchandiseList;
    }
}
