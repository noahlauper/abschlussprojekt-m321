package ch.bbw.snusshop.orderservice.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
  @Id
  @GeneratedValue
  private Long id;
  String paymentMethod;
  @ElementCollection
  List<Long> productIds;


  public Order(String paymentMethod, List<Long> productIds) {
    this.paymentMethod = paymentMethod;
    this.productIds = productIds;
  }
}