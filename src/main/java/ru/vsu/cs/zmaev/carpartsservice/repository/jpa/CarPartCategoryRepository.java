package ru.vsu.cs.zmaev.carpartsservice.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;

@Repository
public interface CarPartCategoryRepository extends JpaRepository<CarPartCategory, Long> {
}
