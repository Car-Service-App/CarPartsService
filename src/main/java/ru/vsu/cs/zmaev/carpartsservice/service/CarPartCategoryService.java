package ru.vsu.cs.zmaev.carpartsservice.service;


import org.springframework.data.domain.Page;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCategoryCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartCategoryRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartCategoryResponseDto;

import java.util.List;

public interface CarPartCategoryService {

    Page<CarPartCategoryResponseDto> findAllWithFilters(EntityPage entityPage, CarPartCategoryCriteriaSearch carPartCategoryCriteriaSearch);

    CarPartCategoryResponseDto findOneById(Long id);

    CarPartCategoryResponseDto save(CarPartCategoryRequestDto carPartCategoryRequestDto);

    CarPartCategoryResponseDto update(Long id, CarPartCategoryRequestDto carPartCategoryRequestDto);

//    List<CarPartCategoryResponseDto> findCategoriesByTypeName(String categoryType);

    void delete(Long id);
}
