package com.example.shop.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart createCart() {
        Cart cart = Cart.builder()
                        .build();

        cartRepository.save(cart);

        return cart;
    }
}
