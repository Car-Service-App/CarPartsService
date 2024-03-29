package ru.vsu.cs.zmaev.carpartsservice.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.CategoryType;

import java.util.List;

@Data
@Entity
@Table(name = "car_part_category")
public class CarPartCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "category_name")
    private CategoryType categoryName;

    @OneToMany(mappedBy = "carPartCategory")
    private List<CarPartType> carPartTypes;

    @Column(name = "image")
    private String image;
}