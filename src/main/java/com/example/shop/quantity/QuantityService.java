package com.example.shop.quantity;

import com.example.shop.cart.Cart;
import com.example.shop.merchandise.Merchandise;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuantityService {

    private final QuantityRepository quantityRepository;

    public void createQuantity(Merchandise merchandise, Cart cart){
        Quantity quantity = Quantity.builder()
                .quantity(1L)
                .cart(cart)
                .merchandise(merchandise)
                .build();

        quantityRepository.save(quantity);
    }

    public void addQuantity(long merchandiseId, long cartId) {
        Quantity quantity = quantityRepository.findByMerchandiseIdAndCartId(merchandiseId, cartId);
        quantity.addQuantityEntity();
        quantityRepository.save(quantity);
    }

    public long subtractQuantity(long merchandiseId, long cartId) {
        Quantity quantity = quantityRepository.findByMerchandiseIdAndCartId(merchandiseId, cartId);
        if(quantity.getQuantity() > 1){
            quantity.subtractQuantityEntity();
            quantityRepository.save(quantity);
            return 1;
        } else if(quantity.getQuantity() == 1){
            quantityRepository.deleteById(quantity.getId());
            return -1;
        }
        return 0;
        }

    public void deleteQuantity(Cart cart) {
        Iterable<Quantity> iterable = getQuantity(cart);
        quantityRepository.deleteAll(iterable);
    }

    public List<Quantity> getQuantity(Cart cart){
        return quantityRepository.findByCartId(cart.getId());
    }
}
