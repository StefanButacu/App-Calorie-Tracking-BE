package ro.ubbcluj.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ro.ubbcluj.app.service.JwtTokenService;

import java.io.IOException;
public class AuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;

    public AuthorizationFilter(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        if( CorsUtils.isPreFlightRequest(request)){
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request, response);
            return;
        }
        // Skip filtering for login request
        if (request.getRequestURI().equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authorizationHeader = request.getHeader("Authorization");

        // Check if the Authorization header is present and formatted correctly
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            // Return an error response if the Authorization header is not present or is not correctly formatted
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header must be provided");
            return;
        }
        else {
            String token = authorizationHeader.substring(7);
            if(jwtTokenService.isTokenExpired(token)) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header expired");
                return;
            }

        }

        // If everything is OK, allow the request to proceed
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.contains("login") || path.contains("user");

    }
}
