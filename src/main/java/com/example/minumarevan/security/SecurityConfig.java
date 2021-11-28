package com.example.minumarevan.security;

import com.example.minumarevan.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // add your resources here. By default, spring security blocks all resources that is not under /resources/**
                .antMatchers(HttpMethod.GET, "/", "/index", "/home", "/login", "/webjars/**", "/js/**", "/static/**", "/css/**", "/images/**", "/register").permitAll()
                // prevent spring security from blocking some pages that doesn't require authentication to be access here.
                .antMatchers("/forgot-password", "/change-password").permitAll()
                .antMatchers(HttpMethod.POST, "/register/save").permitAll()
                .anyRequest().authenticated()
                .and()
                // login configuration
                .formLogin()
                .loginPage("/login") // can either be mapping or file
                .permitAll(true)
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
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

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(new MyUserDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
