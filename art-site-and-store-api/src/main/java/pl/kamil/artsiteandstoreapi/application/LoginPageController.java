package pl.kamil.artsiteandstoreapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kamil.artsiteandstoreapi.application.dtos.LoginFormDTO;

@Slf4j
@Profile(value = {"dev", "prod"})
@RestController
@RequiredArgsConstructor
public class LoginPageController {
  private final AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginFormDTO loginForm) {

    try {
      Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password())
      );

      log.info("User authenticated: {}", auth.getName());

      return ResponseEntity.ok("Login successful");

    } catch (Exception ex) {
      log.warn("Failed login for user: {}", loginForm.username());
      return ResponseEntity.badRequest().body("Login failed");
    }
  }
}
