package ch.bbw.snusshop.orderservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.bbw.snusshop.orderservice.model.OrderResponse;
import ch.bbw.snusshop.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/snus-shop/orders")
@RequiredArgsConstructor
@Log4j2
public class OrderController {

  private final OrderService orderService;

  @PostMapping("/productIds/{paymentMethod}")
  public void createOrder(
      @PathVariable List<Long> productIds,
      @PathVariable String paymentMethod,
      @RequestHeader("Authorization") String jwtToken
  ) {
    this.orderService.createOrder(productIds, paymentMethod, jwtToken);
  }

  @GetMapping()
  public ResponseEntity<List<OrderResponse>> getOrders(
      @RequestHeader("Authorization") String jwtToken
  ) {
    return this.orderService.getOrders(jwtToken);
  }

}
