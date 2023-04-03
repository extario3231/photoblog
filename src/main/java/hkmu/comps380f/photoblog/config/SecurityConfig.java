package hkmu.comps380f.photoblog.config;

import hkmu.comps380f.photoblog.service.BlogUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static hkmu.comps380f.photoblog.model.UserRole.ADMIN;
import static hkmu.comps380f.photoblog.model.UserRole.USER;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final BlogUserDetailsService userDetailsService;

    public SecurityConfig(BlogUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/profile", "/upload", "/profile/edit", "/upload/history")
                        .hasAnyRole(USER.name(), ADMIN.name())
                        .requestMatchers("/manage", "/user/add").hasRole(ADMIN.name())
                        .anyRequest().permitAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .clearAuthentication(true)
                        .invalidateHttpSession(true)
                        .permitAll()
                        .logoutSuccessUrl("/")
                )
                .authenticationProvider(authenticationProvider())
                .csrf()
                .and()
                .build();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
