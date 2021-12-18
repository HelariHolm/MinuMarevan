package com.example.minumarevan.repository;

import com.example.minumarevan.model.ContactNumbers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactNumbersRepository extends CrudRepository<ContactNumbers, Integer> {
}
