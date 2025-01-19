package ch.bbw.snusshop.orderservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
  String PaymentMethod;
  List<Product> products;
}
