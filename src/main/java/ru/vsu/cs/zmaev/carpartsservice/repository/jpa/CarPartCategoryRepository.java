package ru.vsu.cs.zmaev.carpartsservice.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarPartCategoryRepository extends JpaRepository<CarPartCategory, Long> {
}
