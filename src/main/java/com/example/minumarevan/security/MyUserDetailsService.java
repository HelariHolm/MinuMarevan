package com.example.minumarevan.security;

import com.example.minumarevan.user.User;
import com.example.minumarevan.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    public UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repo.findByUsername(username);

        user.orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found!"));

        return user.map(CustomUserDetails::new).get();
    }
}
