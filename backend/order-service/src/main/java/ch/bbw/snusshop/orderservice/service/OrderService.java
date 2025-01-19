package ch.bbw.snusshop.orderservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ch.bbw.snusshop.orderservice.client.AuthClient;
import ch.bbw.snusshop.orderservice.client.ProductClient;
import ch.bbw.snusshop.orderservice.model.Order;
import ch.bbw.snusshop.orderservice.model.OrderResponse;
import ch.bbw.snusshop.orderservice.model.Product;
import ch.bbw.snusshop.orderservice.model.User;
import ch.bbw.snusshop.orderservice.repository.OrderRepository;
import ch.bbw.snusshop.orderservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final ProductClient productClient;
  private final AuthClient authClient;
  private final UserRepository userRepository;
  private final OrderRepository orderRepository;

  public void createOrder(List<Long> productIds, String paymentMethod,String jwtToken) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      Order order = new Order(paymentMethod, productIds);
      Order savedOrder =  orderRepository.save(order);
      user.getOrderIds().add(savedOrder.getId());
      userRepository.save(user);
    } else {
      throw new IllegalStateException("Invalid Jwt Token " + jwtToken);
    }
  }


  public ResponseEntity<List<OrderResponse>> getOrders(String jwtToken) {
    User user = authClient.getUserByToken(jwtToken);
    if (user != null) {
      List<OrderResponse> orderResponses = new ArrayList<>();
      for (Order order : this.orderRepository.findAllById(user.getOrderIds())) {
        List<Product> products = productClient.getProductsFromCart(order.getProductIds(), jwtToken);
        orderResponses.add(new OrderResponse(order.getPaymentMethod(), products));
      }
      return ResponseEntity.ok().body(orderResponses);
    } else {
      return ResponseEntity.status(401).build();
    }
  }
}
