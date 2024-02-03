package ru.vsu.cs.zmaev.carpartsservice.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartType;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.PartType;

import java.util.Optional;

@Repository
public interface CarPartTypeRepository extends JpaRepository<CarPartType, Long> {

    Optional<CarPartType> findCarPartTypeByPartType(PartType partType);
}
