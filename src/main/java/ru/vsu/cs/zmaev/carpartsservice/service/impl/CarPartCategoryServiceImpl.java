package ru.vsu.cs.zmaev.carpartsservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCategoryCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartCategoryRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartCategoryResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.CategoryType;
import ru.vsu.cs.zmaev.carpartsservice.domain.mapper.CarPartCategoryMapper;
import ru.vsu.cs.zmaev.carpartsservice.exception.NoSuchEntityException;
import ru.vsu.cs.zmaev.carpartsservice.repository.jpa.CarPartCategoryRepository;
import ru.vsu.cs.zmaev.carpartsservice.repository.criteria.CarPartCategoryCriteriaRepository;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartCategoryService;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarPartCategoryServiceImpl implements CarPartCategoryService {
    private final CarPartCategoryRepository carPartCategoryRepository;
    private final CarPartCategoryCriteriaRepository carPartCategoryCriteriaRepository;
    private final CarPartCategoryMapper carPartCategoryMapper;

    @Override
    public Page<CarPartCategoryResponseDto> findAllWithFilters(
            EntityPage entityPage,
            CarPartCategoryCriteriaSearch carPartCategoryCriteriaSearch) {
        return carPartCategoryCriteriaRepository
                .findAllWithFilters(entityPage, carPartCategoryCriteriaSearch)
                .map(carPartCategoryMapper::toDto);
    }

    @Override
    public CarPartCategoryResponseDto findOneById(Long id) {
        CarPartCategory carPartCategory = carPartCategoryRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPartCategory.class, id));
        return carPartCategoryMapper.toDto(carPartCategory);
    }

    @Override
    public CarPartCategoryResponseDto save(CarPartCategoryRequestDto carPartCategoryRequestDto) {
        CarPartCategory carPartCategory = carPartCategoryMapper.toEntity(carPartCategoryRequestDto);
        carPartCategory.setCategoryName(carPartCategoryRequestDto.getCategoryName());
        carPartCategory = carPartCategoryRepository.save(carPartCategory);
        return carPartCategoryMapper.toDto(carPartCategory);
    }

    @Override
    public CarPartCategoryResponseDto update(Long id, CarPartCategoryRequestDto carPartCategoryRequestDto) {
        return carPartCategoryRepository
                .findById(id)
                .map(existingEvent -> {
                    carPartCategoryMapper.partialUpdate(existingEvent, carPartCategoryRequestDto);
                    return existingEvent;
                })
                .map(carPartCategoryRepository::save)
                .map(carPartCategoryMapper::toDto)
                .orElseThrow(() -> new NoSuchEntityException(CarPartCategory.class, id));
    }

    @Override
    public void delete(Long id) {
        CarPartCategory carPartCategory = carPartCategoryRepository
                .findById(id)
                .orElseThrow(() ->
                new NoSuchEntityException(CarPartCategory.class, id));
        carPartCategoryRepository.delete(carPartCategory);
    }
}
