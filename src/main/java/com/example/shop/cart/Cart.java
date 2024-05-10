package com.example.shop.cart;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany
    private List<Merchandise> merchandiseList = new ArrayList<>();

    private SiteUser cartUser;

    public void changeCart(SiteUser cartUser, List<Merchandise> merchandiseList){
        this.merchandiseList = merchandiseList;
    }
}
