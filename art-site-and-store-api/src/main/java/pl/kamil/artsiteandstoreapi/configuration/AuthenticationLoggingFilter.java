package pl.kamil.artsiteandstoreapi.configuration;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter extends OncePerRequestFilter {

  private final Logger logger = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {

    logger.info("Successfully authenticated request");

    filterChain.doFilter(request, response);
  }
}
