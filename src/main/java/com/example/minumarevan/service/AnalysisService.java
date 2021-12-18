package com.example.minumarevan.service;

import com.example.minumarevan.model.Analysis;
import com.example.minumarevan.model.ContactNumbers;
import com.example.minumarevan.repository.AnalysisRepository;
import com.example.minumarevan.repository.ContactNumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnalysisService {

    @Autowired
    public AnalysisRepository repo;

    public void save(Analysis analysis) {
        repo.save(analysis);
    }

    public Optional<Analysis> get(Integer id) {
        return repo.findById(id);
    }
}
