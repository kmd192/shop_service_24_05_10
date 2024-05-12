package com.example.shop.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    private String address;

    private double cash;

    /*@OneToOne(mappedBy = "cartUser", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Cart cart;*/

    public void addCash(double add){
        this.cash = cash + add;
    }

    public void changeUserBasicInfo(String username, String password, String email, String address){
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }
}
