package com.example.shop;

import com.example.shop.cart.Cart;
import com.example.shop.cart.CartRepository;
import com.example.shop.cart.CartService;
import com.example.shop.category.CategoryRepository;
import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseRepository;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.quantity.QuantityRepository;
import com.example.shop.quantity.QuantityService;
import com.example.shop.review.ReviewRepository;
import com.example.shop.user.UserRepository;
import com.example.shop.user.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CartRepositoryTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MerchandiseRepository merchandiseRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private QuantityService quantityService;

    @Autowired
    private QuantityRepository quantityRepository;

    @BeforeEach
    void beforeEach(){
        clearData();
        createSampleData();
    }

    private void clearData() {
        UserRepositoryTests.clearAllData(quantityRepository, cartRepository, categoryRepository, reviewRepository, userRepository, merchandiseRepository);
    }

    private void createSampleData(){

        CategoryRepositoryTests.createSampleData(categoryRepository);
        UserRepositoryTests.createSampleData(userService);
        MerchandiseRepositoryTests.createSampleData(merchandiseService, userRepository);
        createSampleData(quantityService, userService, cartRepository, merchandiseRepository, cartService);
    }

    public static void createSampleData(QuantityService quantityService, UserService userService, CartRepository cartRepository, MerchandiseRepository merchandiseRepository, CartService cartService){
        cartService.createCart(userService.getUser(1L));
        cartService.createCart(userService.getUser(2L));
        Cart cart = cartRepository.findById(1L).get();
        Merchandise merchandise = merchandiseRepository.findById(1L).get();
        Merchandise merchandise2 = merchandiseRepository.findById(2L).get();
        cartService.addMerchandise(cart, merchandise);
        cartService.addMerchandise(cart, merchandise2);
    }

    @Transactional
    @Rollback(value = false)
    @Test
    void 셋팅(){
        Cart cart = cartRepository.findById(1L).get();
        assertThat(cart.getMerchandiseList().size()).isEqualTo(2L);
    }
}
