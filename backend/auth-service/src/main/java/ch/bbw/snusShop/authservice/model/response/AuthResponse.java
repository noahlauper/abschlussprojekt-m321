package ch.bbw.snusShop.authservice.model.response;

import lombok.Data;

@Data
public class AuthResponse {
  private String jwtToken;
  public AuthResponse(String jwtToken) {
    this.jwtToken = jwtToken;
  }
}
