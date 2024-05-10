package com.example.shop.user;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String username;

    @Column(length = 20)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(length = 100)
    private String address;

    private double cash;

    public void changeUserBasicInfo(String username, String password, String email, String address){
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public void addCash(double add){
        this.cash = cash + add;
    }
}
