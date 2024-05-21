package com.example.shop.cart;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    public Cart createCart(SiteUser siteUser) {
        Cart cart = Cart.builder()
                        .cartUser(siteUser)
                        .build();

        cartRepository.save(cart);

        return cart;
    }

    public void addMerchandise(Cart cart, Merchandise merchandise) {
        cartRepository.save(cart.addMerchandiseEntity(merchandise));
    }
}
