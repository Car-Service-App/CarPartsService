package ru.vsu.cs.zmaev.carpartsservice.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "car_part_analog")
public class CarPartAnalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "car_part_id", referencedColumnName = "id")
    private CarPart carPart;

    @Column(name = "oem")
    private String oem;

    @Column(name = "image")
    @Lob
    private byte[] image;
}
