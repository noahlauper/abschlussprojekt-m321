package ch.bbw.snusshop.orderservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import ch.bbw.snusshop.orderservice.model.Product;


@FeignClient(name = "product-cart-service", url = "http://localhost:8222/snus-shop/products")
public interface ProductClient {

    @GetMapping("/get-by-id/{productId}")
    Product getProductById(
            @PathVariable Long productId,
        @RequestHeader("Authorization") String token

    );

    @PostMapping("/get-from-cart")
    List<Product> getProductsFromCart(
            @RequestBody List<Long> productIds,
              @RequestHeader("Authorization") String token
    );
}
