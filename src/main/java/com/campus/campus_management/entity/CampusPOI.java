package com.campus.campus_management.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "campus_poi")
@Data // Lombok: Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: Generates no-arg constructor
@AllArgsConstructor // Lombok: Generates constructor with all fields
public class CampusPOI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // For PostgreSQL BIGSERIAL
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double latitude; // 纬度

    @Column(nullable = false)
    private Double longitude; // 经度

    private String type; // 例如: 教学楼, 食堂, 宿舍, 体育馆等

    @Column(columnDefinition = "TEXT") // For longer descriptions
    private String description;

    private String imageUrl;
}