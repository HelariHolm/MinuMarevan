package com.example.minumarevan.security;

import com.example.minumarevan.user.User;
import com.example.minumarevan.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

        @Autowired
        private UserRepository repo;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
            User user = repo.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException(username);
            }
            return new MyUserPrincipal(user);
        }

}
