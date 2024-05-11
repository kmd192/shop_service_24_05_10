package com.example.shop.user;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Getter
public class SiteUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30)
    private String username;

    @Column(length = 40)
    private String password;

    @Column(unique = true)
    private String email;

    @Column(length = 100)
    private String address;

    @ColumnDefault("200000")
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
