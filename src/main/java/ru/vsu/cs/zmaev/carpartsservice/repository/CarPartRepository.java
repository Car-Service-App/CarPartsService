package ru.vsu.cs.zmaev.carpartsservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;

public interface CarPartRepository extends JpaRepository<CarPart, Long> {
}
