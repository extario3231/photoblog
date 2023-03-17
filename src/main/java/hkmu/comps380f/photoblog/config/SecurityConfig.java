package hkmu.comps380f.photoblog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {
        return http
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.GET, "/myprofile").permitAll()
//                .requestMatchers(HttpMethod.GET).permitAll()
//                .requestMatchers(HttpMethod.POST).permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login").permitAll()
//                .and()
//                .csrf()
                .httpBasic()
                .and().build();
    }

    // Maybe for later use
//    @Bean
//    public static BCryptPasswordEncoder getPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
