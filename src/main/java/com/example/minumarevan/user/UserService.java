package com.example.minumarevan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    public UserRepository repo;

    public void save(User user) {
        repo.save(user);
    }
}
