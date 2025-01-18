package ch.bbw.snusShop.authservice.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ch.bbw.snusShop.authservice.model.User;
import ch.bbw.snusShop.authservice.model.request.LoginRequest;
import ch.bbw.snusShop.authservice.model.request.RegisterRequest;
import ch.bbw.snusShop.authservice.model.response.AuthResponse;
import ch.bbw.snusShop.authservice.model.response.TokenValidation;
import ch.bbw.snusShop.authservice.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtil jwtUtil;
  private final UserDetailsService userDetailsService;
  private final JwtService jwtService;
  public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest) {
    if (!userRepository.existsByEmail(registerRequest.getEmail())) {
      User user = new User(registerRequest.getFirstName(), registerRequest.getLastName(), registerRequest.getEmail(), passwordEncoder.encode(registerRequest.getPassword()));
      this.userRepository.save(user);
      return ResponseEntity.ok().body(new AuthResponse(this.jwtService.generateToken(user.getEmail())));
    } else {
      return ResponseEntity.status(409).body(null);
    }
  }

  public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
    Optional<User> userOptional = userRepository.findByEmail(loginRequest.getEmail());
    if (userOptional.isPresent()) {
      User user = userOptional.get();
      if (passwordEncoder.matches(loginRequest.getPassword(), user.getPw())) {
        AuthResponse authResponse = new AuthResponse(jwtService.generateToken(user.getEmail()));
        return ResponseEntity.ok(authResponse);
      } else {
        log.info("wrong email or password");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
      }
    } else {
      log.info("user not found username: {}", loginRequest.getEmail());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  public ResponseEntity<User> getUserById(String jwtToken) {
    log.info("inside of get User");
    Optional<User> userOptional = userRepository.findByEmail(jwtUtil.extractUsername(jwtToken));
    return userOptional.map(user -> ResponseEntity.status(200).body(user))
        .orElseGet(() -> ResponseEntity.status(409).build());
  }

  public TokenValidation isTokenValid(String jwtToken) {
    String username = jwtUtil.extractUsername(jwtToken);
    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
    return new TokenValidation(jwtService.isTokenValid(jwtToken, userDetails));
  }
}
