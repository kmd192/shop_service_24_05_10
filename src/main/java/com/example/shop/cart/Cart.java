package com.example.shop.cart;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.quantity.Quantity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "cart")
    private final List<Merchandise> merchandiseList = new ArrayList<>();

    @OneToMany(mappedBy = "cart")
    private List<Quantity> quantity;

    public void changeCart(String merchandiseList){
    }
}
