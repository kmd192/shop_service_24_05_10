package com.example.shop.quantity;

import com.example.shop.cart.Cart;
import com.example.shop.merchandise.Merchandise;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Quantity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    private Merchandise merchandise;

    public void addQuantityEntity() {
        this.quantity = quantity + 1;
    }

    public void subtractQuantityEntity() {
        this.quantity = quantity - 1;

    }

}
