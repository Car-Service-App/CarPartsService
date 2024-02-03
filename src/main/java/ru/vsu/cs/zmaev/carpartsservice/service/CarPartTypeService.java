package ru.vsu.cs.zmaev.carpartsservice.service;

import org.springframework.data.domain.Page;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartTypeCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartTypeRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartTypeResponseDto;

public interface CarPartTypeService {
    Page<CarPartTypeResponseDto> findAllWithFilters(EntityPage entityPage, CarPartTypeCriteriaSearch carPartTypeCriteriaSearch);

    CarPartTypeResponseDto findOneById(Long id);

    CarPartTypeResponseDto save(CarPartTypeRequestDto carPartTypeRequestDto);

    CarPartTypeResponseDto update(Long id, CarPartTypeRequestDto carPartTypeRequestDto);

    void delete(Long id);
}
