package com.example.minumarevan.security;

import com.example.minumarevan.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepository userRepository;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    // add your resources here. By default, spring security blocks all resources that is not under /resources/**
                    .antMatchers(HttpMethod.GET, "/", "/webjars/**", "/js/**", "/css/**", "/images/**", "/register").permitAll()
                    // prevent spring security from blocking some pages that doesn't require authentication to be access here.
                    .antMatchers("/forgot-password", "/change-password").permitAll()
                    .antMatchers(HttpMethod.POST, "/register/save").permitAll()
                    .anyRequest().authenticated()
                .and()
                // login configuration
                .formLogin()
                    .loginPage("/") // can either be mapping or file
                    .permitAll(true)
                    .failureUrl("/login?error=true")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successForwardUrl("/index")
                .and()
                // logout configuration
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .clearAuthentication(true)
                    .permitAll();
    }



}
