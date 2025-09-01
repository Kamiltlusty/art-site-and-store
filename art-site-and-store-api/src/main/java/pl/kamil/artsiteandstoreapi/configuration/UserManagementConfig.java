package pl.kamil.artsiteandstoreapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class UserManagementConfig {
  @Bean
  UserDetailsService userDetailsService() {
    var admin = User
      .withUsername("admin")
      .password("{bcrypt}" + new BCryptPasswordEncoder().encode("admin"))
      .roles("ADMIN")
      .build();

    return new InMemoryUserDetailsManager(admin);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    Map<String, PasswordEncoder> encoders = new HashMap<>();

    encoders.put("bcrypt", new BCryptPasswordEncoder());
    encoders.put("noop", NoOpPasswordEncoder.getInstance());

    return new DelegatingPasswordEncoder("bcrypt", encoders);
  }

}
