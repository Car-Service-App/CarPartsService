package ru.vsu.cs.zmaev.carpartsservice.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartType;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.CategoryType;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.PartType;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarPartTypeRepository extends JpaRepository<CarPartType, Long> {

    Optional<CarPartType> findCarPartTypeByPartType(PartType partType);

//    @Query("SELECT cpt FROM CarPartType cpt WHERE cpt.carPartCategory.id = " +
//            "(SELECT cpc.id FROM CarPartCategory cpc WHERE cpc.categoryName = :categoryName)")
    @Query("SELECT cpt FROM CarPartType cpt WHERE cpt.carPartCategory.categoryName = :categoryName")
    List<CarPartType> findCarPartTypeByCarPartCategory(CategoryType categoryName);
}
