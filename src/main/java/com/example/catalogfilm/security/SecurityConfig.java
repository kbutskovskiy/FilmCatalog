package com.example.catalogfilm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity
                .csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/helloUser").hasRole("USER")
                .requestMatchers("/helloAdmin").hasRole("ADMIN")
                .requestMatchers("/helloAny").permitAll()
                .requestMatchers(HttpMethod.GET, "/director").permitAll()
                .requestMatchers(HttpMethod.POST, "/director").permitAll()
                .requestMatchers(HttpMethod.GET, "/film").permitAll()
                .requestMatchers(HttpMethod.POST, "/film").permitAll()
                .and()
                .httpBasic()
                .and()
                .authenticationManager(authenticationManager(httpSecurity))
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {

        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("10072003"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, admin);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}
