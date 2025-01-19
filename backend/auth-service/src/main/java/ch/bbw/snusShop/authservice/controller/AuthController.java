package ch.bbw.snusShop.authservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.bbw.snusShop.authservice.model.User;
import ch.bbw.snusShop.authservice.model.request.LoginRequest;
import ch.bbw.snusShop.authservice.model.request.RegisterRequest;
import ch.bbw.snusShop.authservice.model.response.AuthResponse;
import ch.bbw.snusShop.authservice.model.response.TokenValidation;
import ch.bbw.snusShop.authservice.repository.UserRepository;
import ch.bbw.snusShop.authservice.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/snus-shop/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;
  private final UserRepository userRepository;

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
    return this.authService.register(registerRequest);
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest);
  }

  @GetMapping("/is-token-valid")
  public TokenValidation isTokenValid(
      @RequestHeader("Authorization") String jwtToken
  ) {
    return this.authService.isTokenValid(jwtToken.substring(7));
  }

  @GetMapping("/get-user")
  public ResponseEntity<User> getUserById(
      @RequestHeader("Authorization") String jwtToken
  ) {
    return authService.getUserById(jwtToken.substring(7));
  }

  @GetMapping("/get-users")
  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
}
