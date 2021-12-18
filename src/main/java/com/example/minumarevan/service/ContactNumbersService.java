package com.example.minumarevan.service;

import com.example.minumarevan.model.ContactNumbers;
import com.example.minumarevan.repository.ContactNumbersRepository;
import com.example.minumarevan.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContactNumbersService {

    @Autowired
    public ContactNumbersRepository repo;

    public void save(ContactNumbers numbers) {
        repo.save(numbers);
    }

    public Optional<ContactNumbers> get(Integer id) {
        return repo.findById(id);
    }
}
