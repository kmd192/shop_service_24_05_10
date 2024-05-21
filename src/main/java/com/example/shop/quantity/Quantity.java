package com.example.shop.quantity;

import com.example.shop.cart.Cart;
import com.example.shop.merchandise.Merchandise;
import jakarta.persistence.*;

@Entity
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Merchandise merchandise;
}
