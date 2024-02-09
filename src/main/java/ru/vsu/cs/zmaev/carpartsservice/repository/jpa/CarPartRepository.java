package ru.vsu.cs.zmaev.carpartsservice.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;

import java.util.Optional;

public interface CarPartRepository extends JpaRepository<CarPart, Long> {
    Optional<CarPart> findCarPartByOem(String oem);
}
