package ro.ubbcluj.app.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.ubbcluj.app.service.JwtTokenService;

@Configuration
public class FilterConfig {

    private final JwtTokenService jwtTokenService;

    public FilterConfig(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @Bean
    public FilterRegistrationBean<AuthorizationFilter> authorizationFilter(){
        FilterRegistrationBean<AuthorizationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AuthorizationFilter(jwtTokenService));
        registrationBean.addUrlPatterns("/api/*"); // define the URL patterns to apply this filter

        return registrationBean;
    }
}

