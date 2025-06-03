package com.campus.campus_management.repository;

import com.campus.campus_management.entity.CampusPOI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampusPOIRepository extends JpaRepository<CampusPOI, Long> {
    // 根据名称模糊查询
    List<CampusPOI> findByNameContainingIgnoreCase(String name);
}