package ch.bbw.snusshop.orderservice.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

  @Id
  private Long id;

  private String firstName;

  private String lastName;

  private String email;

  private String pw;

  @ElementCollection
  private List<Long> shoppingCartList;

  @ElementCollection
  private List<Long> orderIds;


  public User(String firstName, String lastName, String email, String pw) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.pw = pw;
    this.shoppingCartList = new ArrayList<>();
    this.orderIds = new ArrayList<>();
  }
}
