package com.example.shop.cart;

import com.example.shop.merchandise.Merchandise;
import com.example.shop.merchandise.MerchandiseService;
import com.example.shop.quantity.QuantityService;
import com.example.shop.user.SiteUser;
import com.example.shop.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final UserService userService;

    private final QuantityService quantityService;

    private final MerchandiseService merchandiseService;

    private final CartService cartService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public String cart(Principal principal, Model model){
        SiteUser siteUser = userService.getUser(principal.getName());
        model.addAttribute("user", siteUser);
        return "cart";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/put/ajax")
    public ResponseEntity<?> put(@RequestParam String id, Principal principal, Model model){
        SiteUser siteUser = userService.getUser(principal.getName());
        Cart cart = siteUser.getCart();
        Merchandise merchandise = merchandiseService.getMerchandise(Integer.parseInt(id));
        model.addAttribute("user", siteUser);
        if(cartService.isMerchandiseInCart(cart, merchandise)){
            return ResponseEntity.ok(false);
        }
        cartService.addMerchandise(cart, merchandise);
        return ResponseEntity.ok(true);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/increase/{id}")
    public String increase(@PathVariable long id, Principal principal, Model model){
        SiteUser siteUser = userService.getUser(principal.getName());
        Cart cart = siteUser.getCart();
        Merchandise m = merchandiseService.getMerchandise(id);
        quantityService.addQuantity(m.getId(), cart.getId());
        model.addAttribute("user", siteUser);
        if(siteUser.getCart().getMerchandiseList().size() >= 3) {
            return String.format("redirect:/cart#merchandise_%d", id);
        }
        return "cart";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable long id, Principal principal, Model model){
        SiteUser siteUser = userService.getUser(principal.getName());
        Merchandise merchandise = merchandiseService.getMerchandise(id);
        Cart cart = siteUser.getCart();
        if(quantityService.subtractQuantity(id, cart.getId()) == -1){
            cartService.deleteMerchandise(merchandise, cart);
        }
        model.addAttribute("user", siteUser);
        if(siteUser.getCart().getMerchandiseList().size() >= 3) {
            return String.format("redirect:/cart#merchandise_%d", id);
        }
        return "cart";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete")
    public String delete(Principal principal, Model model){
        SiteUser siteUser = userService.getUser(principal.getName());
        Cart cart = siteUser.getCart();
        cartService.deleteAllMerchandise(cart);
        model.addAttribute("user", siteUser);
        return "cart";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/buy")
    public String buy(Principal principal, Model model){
        SiteUser siteUser = userService.getUser(principal.getName());
        Cart cart = siteUser.getCart();
        cartService.buyMerchandise(cart);
        model.addAttribute("user", siteUser);
        return "cart";
    }

}
