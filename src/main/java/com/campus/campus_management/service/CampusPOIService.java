package com.campus.campus_management.service;

import com.campus.campus_management.entity.CampusPOI;
import com.campus.campus_management.repository.CampusPOIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampusPOIService {

    @Autowired
    private CampusPOIRepository campusPOIRepository;

    public List<CampusPOI> getAllPOIs() {
        return campusPOIRepository.findAll();
    }

    public Optional<CampusPOI> getPOIById(Long id) {
        return campusPOIRepository.findById(id);
    }

    public List<CampusPOI> searchPOIsByName(String name) {
        return campusPOIRepository.findByNameContainingIgnoreCase(name);
    }

    public CampusPOI savePOI(CampusPOI poi) {
        return campusPOIRepository.save(poi);
    }

    public void deletePOI(Long id) {
        campusPOIRepository.deleteById(id);
    }
}