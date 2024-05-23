package com.example.shop.cart;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.quantity.QuantityService;
import com.example.shop.user.SiteUser;
import com.example.shop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;

    private final QuantityService quantityService;

    private final UserService userService;

    private final MerchandiseService merchandiseService;

    public Cart createCart(SiteUser siteUser) {
        Cart cart = Cart.builder()
                        .cartUser(siteUser)
                        .build();

        cartRepository.save(cart);

        return cart;
    }

    public void addMerchandise(Cart cart, Merchandise merchandise) {
        quantityService.createQuantity(merchandise, cart);
        cartRepository.save(cart.addMerchandiseEntity(merchandise));
    }

    public boolean isMerchandiseInCart(Cart cart, Merchandise merchandise) {
        return cart.getMerchandiseList().contains(merchandise);
    }

    public void deleteMerchandise(Merchandise merchandise, Cart cart) {
        cart.getMerchandiseList().remove(merchandise);
        cartRepository.save(cart);
    }

    public void deleteAllMerchandise(Cart cart) {
        cart.getMerchandiseList().clear();
        quantityService.deleteQuantity(cart);
        cartRepository.save(cart);
    }
}
