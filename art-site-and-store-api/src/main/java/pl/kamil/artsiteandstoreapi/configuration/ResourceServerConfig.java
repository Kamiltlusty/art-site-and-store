package pl.kamil.artsiteandstoreapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ResourceServerConfig {

    @Value("${keySetURI}")
    private String keySetURI;

    @Bean
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
        http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults());
        http.oauth2ResourceServer(c -> c.jwt(
                        j -> j.jwkSetUri(keySetURI)
                ))
                .authorizeHttpRequests(
                        c -> c.
                                requestMatchers("/carousel/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/carousel/manage").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/carousel/manage").hasRole("ADMIN")
                                .anyRequest().authenticated()
                );
        return http.build();
    }

}
