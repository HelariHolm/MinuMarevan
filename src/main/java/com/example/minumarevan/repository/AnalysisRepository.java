package com.example.minumarevan.repository;

import com.example.minumarevan.model.Analysis;
import com.example.minumarevan.model.ContactNumbers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends CrudRepository<Analysis, Integer> {
}
