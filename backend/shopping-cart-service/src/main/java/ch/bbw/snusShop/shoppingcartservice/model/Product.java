package ch.bbw.snusShop.shoppingcartservice.model;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private int price;
    private String description;
}
