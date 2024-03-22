package ru.vsu.cs.zmaev.carpartsservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseDto;

public interface CarPartService {
    Page<CarPartResponseDto> findAllWithFilters(EntityPage entityPage, CarPartCriteriaSearch carPartCriteriaSearch);

    Page<CarPartResponseDto> findAll(Pageable pageable);

    CarPartResponseDto findOneById(Long id);
    CarPartResponseDto findOneByOem(String oem);

    CarPartResponseDto save(CarPartRequestDto carPartRequestDto);

    CarPartResponseDto update(Long id, CarPartRequestDto carPartRequestDto);

    void delete(Long id);
}
