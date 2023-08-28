package com.example.demo;
import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.cors()
        	.and()
            .authorizeRequests(authorizeRequests ->
                authorizeRequests
                    //.antMatchers("/users/login").authenticated()
                    .antMatchers("/users/getAll").hasRole("ADMIN")
                    .antMatchers("/users/getAll").authenticated()
                   // .antMatchers("/users/delete/{user_id}").authenticated()
                    .anyRequest().permitAll()
            )
            .httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public UserDetailsService userDetailsService() {
PasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Generate a BCrypt-encoded password
        String password = "password";
        String encodedPassword = encoder.encode(password);
        System.out.println("BCrypt-encoded password: " + encodedPassword);

        UserDetails userDetails = User.builder()
            .username("user")
            .password(encodedPassword) // Use the encoded password here
            .roles("USER")
            .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("password"))
                .roles("ADMIN")
                .build();
        
      return new InMemoryUserDetailsManager(userDetails, admin);
        }

        @Bean
        public SecurityFilterChain adminSecurityFilterChain(HttpSecurity http) throws Exception {
            http
                .antMatcher("/admin/**")
                .authorizeRequests(authorizeRequests ->
                    authorizeRequests
                        .anyRequest().hasRole("ADMIN")
                )
                .httpBasic();

            return http.build();
        }
       
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:4200")); // Allow requests from this origin
//        corsConfig.addAllowedMethod("*");
//        corsConfig.addAllowedHeader("*");
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        return source;
//    }
}
