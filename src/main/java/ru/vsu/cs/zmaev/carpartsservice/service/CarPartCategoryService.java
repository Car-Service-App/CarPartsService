package ru.vsu.cs.zmaev.carpartsservice.service;


import org.springframework.data.domain.Page;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCategoryCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartCategoryRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartCategoryResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;

public interface CarPartCategoryService {

    Page<CarPartCategoryResponseDto> findAllWithFilters(EntityPage entityPage, CarPartCategoryCriteriaSearch carPartCategoryCriteriaSearch);

    CarPartCategory findOneById(Long id);

    CarPartCategory save(CarPartCategoryRequestDto carPartCategoryRequestDto);

    CarPartCategory update(Long id, CarPartCategoryRequestDto carPartCategoryRequestDto);

    void delete(Long id);
}
