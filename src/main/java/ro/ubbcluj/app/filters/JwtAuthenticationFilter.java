package ro.ubbcluj.app.filters;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.ubbcluj.app.domain.User;
import ro.ubbcluj.app.repository.UserRepository;
import ro.ubbcluj.app.service.JwtTokenService;

import java.io.IOException;
import java.security.SignatureException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtTokenService jwtTokenService, UserRepository userRepository) {
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            try {
                if (jwtTokenService.validateToken(token)) {
                    String userId = jwtTokenService.extractId(token);
                    Optional<User> userOptional = userRepository.findById(Long.parseLong(userId));

                    if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        request.setAttribute("userId", user.getId());
                    } else {
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        return;
                    }
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
            }
            catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | IllegalArgumentException | io.jsonwebtoken.SignatureException e) {
                // handle invalid token
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                response.getWriter().write("Invalid token");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
