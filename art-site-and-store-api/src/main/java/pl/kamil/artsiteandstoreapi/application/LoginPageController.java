package pl.kamil.artsiteandstoreapi.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.kamil.artsiteandstoreapi.domain.dtos.LoginFormDTO;

@Slf4j
@RestController
@RequiredArgsConstructor
public class LoginPageController {
  private static final Logger logger = LoggerFactory.getLogger(LoginPageController.class);
  private final AuthenticationManager authenticationManager;

  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginFormDTO loginForm) {

    try {
      Authentication auth = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginForm.username(), loginForm.password())
      );

      logger.info("User authenticated: {}", auth.getName());

      return ResponseEntity.ok("Login successful");

    } catch (Exception ex) {
      logger.warn("Failed login for user: {}", loginForm.username());
      return ResponseEntity.badRequest().body("Login failed");
    }
  }

}
