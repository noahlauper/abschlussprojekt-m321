package ch.bbw.snusShop.shoppingcartservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import ch.bbw.snusShop.shoppingcartservice.client.AuthClient;
import ch.bbw.snusShop.shoppingcartservice.client.ProductClient;
import ch.bbw.snusShop.shoppingcartservice.model.*;
import ch.bbw.snusShop.shoppingcartservice.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@AllArgsConstructor
@Log4j2
public class ShoppingBasketService {

  private final ProductClient productClient;
  private final AuthClient authClient;
  private final UserRepository userRepository;

  public ShoppingCartResponse addToBasket(Long productId, String jwtToken) {
    User user = authClient.getUserByToken(jwtToken);
    System.out.println(user.getProductIdsInCart());
    System.out.println(user.getShoppingCartList());
    if (user != null) {
      user.getShoppingCartList().add(productId);
      user.getProductIdsInCart().add(productId);
      userRepository.save(user);
      return new ShoppingCartResponse(user.getShoppingCartList());
    } else {
      throw new IllegalStateException("Invalid Jwt Token " + jwtToken);
    }
  }


  public ResponseEntity<List<Product>> getProductsInCart(String jwtToken) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      List<Long> licenseIds = user.getShoppingCartList();
      return ResponseEntity.ok().body(productClient.getProductsFromCart(licenseIds, jwtToken));
    } else {
      log.warn("Invalid Jwt Token {}", jwtToken);
      return ResponseEntity.status(401).build();
    }
  }

  public ShoppingCartResponse removeFromCart(Long productId, String jwtToken) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      int index = user.getShoppingCartList().indexOf(productId);
      if (index >= 0) {
        user.getShoppingCartList().remove(index);
      }
      userRepository.save(user);
      return new ShoppingCartResponse(user.getShoppingCartList());
    } else {
      log.warn("Invalid Jwt Token {}", jwtToken);
      throw new IllegalStateException("Invalid Jwt Token " + jwtToken);
    }
  }

  public ShoppingCartResponse removeAllProducts(List<Long> productIds, String jwtToken) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      for (Long productId : productIds) {
        int index = user.getShoppingCartList().indexOf(productId);
        if (index != -1) {
          user.getShoppingCartList().remove(index);
        }
      }
      this.userRepository.save(user);
      return new ShoppingCartResponse(user.getShoppingCartList());
    } else {
      log.warn("couldnt find shopping Cart with id: {}", 1L);
      throw new IllegalStateException("shopping Cart not found with id: " + 1L);
    }
  }

  public boolean areProductsInCart(String jwtToken) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      return !user.getShoppingCartList().isEmpty();
    } else {
      log.warn("Invalid Jwt Token {}", jwtToken);
      throw new IllegalStateException("Invalid Jwt Token " + jwtToken);
    }
  }
}
