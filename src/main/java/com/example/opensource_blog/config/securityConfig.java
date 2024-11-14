package com.example.opensource_blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;

@EnableWebSecurity
@Configuration
public class securityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/","/users/login", "/accounts/sign-up","/accounts/login").permitAll();
                    auth.anyRequest().authenticated(); // 그 외 요청은 인증 필요
                })
                .formLogin(login -> login
                        .loginPage("/accounts/login")
                        .loginProcessingUrl("/accounts/login")
                        .defaultSuccessUrl("/home",true)
                        .permitAll() // 로그인 페이지는 모두 접근 가능
                ).exceptionHandling(execpion -> execpion.accessDeniedPage("/error/denied"));
        return http.build();
    }
}
