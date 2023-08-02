package com.example.releases.security;

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

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    //.requestMatchers("/**") is a temp fix for SPRING CORS No access-control-allow-origin
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        //all HTTP url besides "/" root must be authenticated
        //so our user can not look at book details
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                );
//        http.cors().and()
//                .authorizeRequests()
//                .requestMatchers("/api/public/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
        return http.build();
    }

    //For authentication, Spring requires that you have a PasswordEncoder and UserDetailSer

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


}
