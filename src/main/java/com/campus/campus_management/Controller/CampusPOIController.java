package com.campus.campus_management.Controller;

import com.campus.campus_management.entity.CampusPOI;
import com.campus.campus_management.service.CampusPOIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pois")
public class CampusPOIController {

    @Autowired
    private CampusPOIService campusPOIService;

    @GetMapping
    public List<CampusPOI> getAllPOIs() {
        return campusPOIService.getAllPOIs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CampusPOI> getPOIById(@PathVariable Long id) {
        Optional<CampusPOI> poi = campusPOIService.getPOIById(id);
        return poi.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public List<CampusPOI> searchPOIs(@RequestParam String name) {
        return campusPOIService.searchPOIsByName(name);
    }

    @PostMapping
    public CampusPOI createPOI(@RequestBody CampusPOI poi) {
        return campusPOIService.savePOI(poi);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CampusPOI> updatePOI(@PathVariable Long id, @RequestBody CampusPOI poiDetails) {
        Optional<CampusPOI> existingPoi = campusPOIService.getPOIById(id);
        if (existingPoi.isPresent()) {
            CampusPOI poi = existingPoi.get();
            poi.setName(poiDetails.getName());
            poi.setLatitude(poiDetails.getLatitude());
            poi.setLongitude(poiDetails.getLongitude());
            poi.setType(poiDetails.getType());
            poi.setDescription(poiDetails.getDescription());
            poi.setImageUrl(poiDetails.getImageUrl());
            return ResponseEntity.ok(campusPOIService.savePOI(poi));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePOI(@PathVariable Long id) {
        if (campusPOIService.getPOIById(id).isPresent()) {
            campusPOIService.deletePOI(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}