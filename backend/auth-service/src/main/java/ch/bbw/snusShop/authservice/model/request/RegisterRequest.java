package ch.bbw.snusShop.authservice.model.request;

import lombok.Data;

@Data
public class RegisterRequest {

  private String firstName;

  private String lastName;

  private String email;

  private String password;

}
