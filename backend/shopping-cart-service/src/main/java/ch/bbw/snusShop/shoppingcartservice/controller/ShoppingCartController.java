package ch.bbw.snusShop.shoppingcartservice.controller;


import ch.bbw.snusShop.shoppingcartservice.model.Product;
import ch.bbw.snusShop.shoppingcartservice.model.ShoppingCartResponse;
import ch.bbw.snusShop.shoppingcartservice.service.ShoppingBasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/snus-shop/cart")
@Log4j2
public class ShoppingCartController {

    private final ShoppingBasketService shoppingBasketService;

    @GetMapping()
    public ResponseEntity<List<Product>> getProductsInCart(
        @RequestHeader("Authorization") String jwtToken
    ) {
        return this.shoppingBasketService.getProductsInCart(jwtToken);
    }

    @GetMapping("/is-cartList-empty")
    public boolean isShoppingCartListEmpty(
        @RequestHeader("Authorization") String jwtToken
    ) {
        return this.shoppingBasketService.areProductsInCart(jwtToken);
    }

    @PutMapping("/add-product/{productId}")
    public ShoppingCartResponse addLicenseToBasket(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwtToken
    ) {
        return this.shoppingBasketService.addToBasket(productId, jwtToken);
    }

    @PatchMapping("/remove-product/by-id/{productId}")
    public ShoppingCartResponse removeLicense(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwtToken
    ) {
        return this.shoppingBasketService.removeFromCart(productId, jwtToken);
    }

    @PatchMapping("/remove-products/{productIds}")
    public ShoppingCartResponse removeLicenses(
            @RequestHeader("Authorization") String jwtToken,
            @PathVariable List<Long> productIds
    ) {
        return this.shoppingBasketService.removeAllProducts(productIds, jwtToken);
    }
}
