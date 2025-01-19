package ch.bbw.snusShop.authservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  private Long id;
  String paymentMethod;
  List<Long> productIds;
}

