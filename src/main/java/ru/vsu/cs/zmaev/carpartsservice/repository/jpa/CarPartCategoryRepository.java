package ru.vsu.cs.zmaev.carpartsservice.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;

import java.util.List;

@Repository
public interface CarPartCategoryRepository extends JpaRepository<CarPartCategory, Long> {
//    @Query("SELECT cat.carPartTypes FROM CarPartCategory cat WHERE cat.carPartTypes = :carPartType")
//    List<CarPartCategory> findCarPartByCarPartType(@Param("carPartType") String carPartType);
}
