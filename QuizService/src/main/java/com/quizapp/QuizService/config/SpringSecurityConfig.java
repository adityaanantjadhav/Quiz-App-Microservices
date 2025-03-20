//package com.quizapp.QuizService.config;
//
//
//import org.springframework.cloud.openfeign.EnableFeignClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//public class SpringSecurityConfig {
//
//    @Bean
//    SecurityFilterChain customFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(request->request.anyRequest().authenticated())
//                .oauth2ResourceServer(cust->cust.jwt(Customizer.withDefaults()));
//        return http.build();
//    }
//}
