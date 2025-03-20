//package com.quizapp.QuestionService.config;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SpringSecurityConfig {
//
//
//    @Bean
//    SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((request)->request.anyRequest().authenticated())
//                .oauth2ResourceServer((cust)->cust.jwt(Customizer.withDefaults()));
//
//        return http.build();
//
//    }
//}
