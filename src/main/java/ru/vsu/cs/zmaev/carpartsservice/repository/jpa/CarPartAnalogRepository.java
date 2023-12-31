package ru.vsu.cs.zmaev.carpartsservice.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartAnalog;

public interface CarPartAnalogRepository extends JpaRepository<CarPartAnalog, Long> {
}
