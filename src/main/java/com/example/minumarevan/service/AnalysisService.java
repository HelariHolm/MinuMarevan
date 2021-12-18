package com.example.minumarevan.service;

import com.example.minumarevan.model.Analysis;
import com.example.minumarevan.model.ContactNumbers;
import com.example.minumarevan.repository.AnalysisRepository;
import com.example.minumarevan.repository.ContactNumbersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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

    public Float getTodaysPills() {
        final List<Analysis> analyses = new ArrayList<>();
        repo.findAll().iterator().forEachRemaining(analysis -> analyses.add(analysis));

        List<Analysis> sorted = analyses.stream().sorted(Comparator.comparing(Analysis::getInrDate)).collect(Collectors.toList());

        final Analysis latestAnalysis = sorted.get(0);

        if (latestAnalysis == null) {
            return 0f;
        } else {
            return latestAnalysis.getInr();
        }
    }
}
