package ru.vsu.cs.zmaev.carpartsservice.service;

import org.springframework.data.domain.Page;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartAnalogCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartAnalogRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartAnalogResponseDto;

public interface CarPartAnalogService {
    Page<CarPartAnalogResponseDto> findAllWithFilters(EntityPage entityPage, CarPartAnalogCriteriaSearch carPartAnalogCriteriaSearch);

    CarPartAnalogResponseDto findOneById(Long id);

    CarPartAnalogResponseDto save(CarPartAnalogRequestDto carPartAnalogRequestDto);

    CarPartAnalogResponseDto update(Long id, CarPartAnalogRequestDto carPartAnalogRequestDto);

    void delete(Long id);
}
