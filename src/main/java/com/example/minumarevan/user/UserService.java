package com.example.minumarevan.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    public UserRepository repo;

    public void save(User user) {
        repo.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID = " + id);
    }

    public User findUserByUsername(String username) throws UserNotFoundException {
        Optional<User> result = repo.findByUsername(username);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with username = " + username);
    }

    public User findUserByEmail(String email) throws UserNotFoundException{
        Optional<User> result = repo.findByEmail(email);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with username = " + email);
    }
}
