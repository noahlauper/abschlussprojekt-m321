package ch.bbw.snusShop.productservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.bbw.snusShop.productservice.repository.ProductRepository;
import ch.bbw.snusShop.productservice.model.*;
import ch.bbw.snusShop.productservice.client.*;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/snus-shop/products")
@RequiredArgsConstructor
@Log4j2
public class ProductController {

  private final ProductRepository productRepository;
  private final AuthClient authClient;

  @GetMapping
  public List<Product> getAllProducts() {
    return this.productRepository.findAll();
  }

  @PostMapping("/get-multiple-products-by-id")
  public ResponseEntity<List<Product>> getMultipleProductsById(
      @RequestBody ProductRetrievalRequest productRetrievalRequest,
        @RequestHeader("Authorization") String jwtToken
  ) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      List<Product> productList = new ArrayList<>();
      for (Long licenseId : productRetrievalRequest.getProductIds()) {
        Optional<Product> licenseOptional = productRepository.findById(licenseId);
        licenseOptional.ifPresent(productList::add);
      }
      return ResponseEntity.ok().body(productList);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

  }

  @GetMapping("/get-by-id/{productId}")
  public ResponseEntity<Product> getProductById(
      @PathVariable Long productId,
      @RequestHeader("Authorization") String jwtToken
  ) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      Optional<Product> licenseOptional = productRepository.findById(productId);
      if (licenseOptional.isPresent()) {
        return ResponseEntity.ok(licenseOptional.get());
      } else {
        throw new NotFoundException("Product with id: " + productId + " not found");
      }
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @PostMapping("/get-products-from-cart")
  public ResponseEntity<List<Product>> getProductsFromCart(
      @RequestBody List<Long> productIds,
      @RequestHeader("Authorization") String jwtToken
  ) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      List<Product> productList = new ArrayList<>();
      for (Long productId : productIds) {
        if (productIds.isEmpty()) {
          return ResponseEntity.ok(productList);
        }
        Optional<Product> productOptional = productRepository.findById(productId);
        log.info("{}", productOptional);
        if (productOptional.isPresent()) {
          log.info("added Product with id: {} to productList", productId);
          productList.add(productOptional.get());
        } else {
          log.warn("Product with id: {} not found", productId);
          throw new NotFoundException("Product with id: " + productId + "not found");
        }
      }
      return ResponseEntity.ok(productList);
    } else {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }
}

