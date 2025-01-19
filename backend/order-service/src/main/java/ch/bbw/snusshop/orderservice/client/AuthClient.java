package ch.bbw.snusshop.orderservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import ch.bbw.snusshop.orderservice.model.User;


@FeignClient(name = "auth-service", url = "http://localhost:8222/snus-shop/auth")
public interface AuthClient {

  @GetMapping("/{userId}")
  User getUserById(@PathVariable Long userId);

  @GetMapping("/get-user")
  User getUserByToken(
      @RequestHeader("Authorization") String token
  );
}
