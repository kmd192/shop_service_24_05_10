package com.example.shop.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    SiteUser addCashEntity(double add){
        this.cash = cash + add;

        return this;
    }

    SiteUser changeUserBasicInfoEntity(String password, String email, String address){
        this.password = password;
        this.email = email;
        this.address = address;

        return this;
    }
}
