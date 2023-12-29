package ru.vsu.cs.zmaev.carpartsservice.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@Table(name = "car_part")
public class CarPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long manufacturerId;

    private Long carId;

    @OneToOne
    @JoinColumn(name = "car_part_type_id", referencedColumnName = "id")
    private CarPartType carPartType;

    @Column(name = "name")
    private String name;

    @Column(name = "oem")
    private String oem;

    @Column(name = "last_price", precision = 10, scale = 2)
    private BigDecimal lastPrice;

    @Column(name = "description")
    private String description;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @OneToMany(mappedBy = "carPart")
    private List<CarPartAnalog> carPartAnalogs;

}
