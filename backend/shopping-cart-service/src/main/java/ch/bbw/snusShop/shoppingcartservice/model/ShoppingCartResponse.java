package ch.bbw.snusShop.shoppingcartservice.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartResponse {

  private List<Long> productList;

}
