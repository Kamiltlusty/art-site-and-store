package pl.kamil.artsiteandstoreapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class WebAuthorizationConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http.formLogin(Customizer.withDefaults());

//    http.exceptionHandling(ex -> {
//      ex.authenticationEntryPoint(new CustomEntryPoint());
//    });
    http.addFilterAfter(
      new AuthenticationLoggingFilter(),
      UsernamePasswordAuthenticationFilter.class
    );
//
////    http.sessionManagement(SessionCreationPolicy.STATELESS);
//
    http.authorizeHttpRequests(auth -> auth
//      .requestMatchers("").permitAll()
      .anyRequest().authenticated()
    );

    return http.build();
  }
}
