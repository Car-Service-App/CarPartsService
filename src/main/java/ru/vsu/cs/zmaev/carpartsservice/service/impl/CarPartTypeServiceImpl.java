package ru.vsu.cs.zmaev.carpartsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartTypeCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartTypeRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartTypeResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartType;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.CategoryType;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.PartType;
import ru.vsu.cs.zmaev.carpartsservice.domain.mapper.CarPartTypeMapper;
import ru.vsu.cs.zmaev.carpartsservice.exception.NoSuchAttributeException;
import ru.vsu.cs.zmaev.carpartsservice.exception.NoSuchEntityException;
import ru.vsu.cs.zmaev.carpartsservice.repository.jpa.CarPartCategoryRepository;
import ru.vsu.cs.zmaev.carpartsservice.repository.jpa.CarPartTypeRepository;
import ru.vsu.cs.zmaev.carpartsservice.repository.criteria.CriteriaRepository;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarPartTypeServiceImpl implements CarPartTypeService {

    private final CarPartTypeRepository carPartTypeRepository;
    private final CriteriaRepository<CarPartType, CarPartTypeCriteriaSearch> carPartTypeCriteriaRepository;
    private final CarPartTypeMapper carPartTypeMapper;

    private final CarPartCategoryRepository carPartCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<CarPartTypeResponseDto> findAllWithFilters(EntityPage entityPage, CarPartTypeCriteriaSearch carPartTypeCriteriaSearch) {
        return carPartTypeCriteriaRepository
                .findAllWithFilters(entityPage, carPartTypeCriteriaSearch)
                .map(carPartTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CarPartTypeResponseDto findOneById(Long id) {
        CarPartType carPartType = carPartTypeRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPartType.class, id));
        return carPartTypeMapper.toDto(carPartType);
    }

    @Override
    @Transactional
    public CarPartTypeResponseDto save(CarPartTypeRequestDto carPartTypeRequestDto) {
        CarPartType carPartType = carPartTypeMapper.toEntity(carPartTypeRequestDto);
        CarPartCategory carPartCategory =
                carPartCategoryRepository.findById(carPartTypeRequestDto.getCarPartCategoryId())
                        .orElseThrow(() -> new NoSuchEntityException(
                                CarPartCategory.class,
                                carPartTypeRequestDto.getCarPartCategoryId()));
        carPartType.setCarPartCategory(carPartCategory);
        carPartType = carPartTypeRepository.save(carPartType);
        return carPartTypeMapper.toDto(carPartType);
    }

    @Override
    @Transactional
    public CarPartTypeResponseDto update(Long id, CarPartTypeRequestDto carPartTypeRequestDto) {
        return carPartTypeRepository
                .findById(id)
                .map(existingEvent -> {
                    carPartTypeMapper.partialUpdate(existingEvent, carPartTypeRequestDto);
                    return existingEvent;
                })
                .map(carPartTypeRepository::save)
                .map(carPartTypeMapper::toDto)
                .orElseThrow(() -> new NoSuchEntityException(CarPartType.class, id));
    }

    @Override
    public List<CarPartTypeResponseDto> findCarPartTypeByCarPartCategory(String categoryName) {
        try {
            CategoryType.valueOf(categoryName);
        } catch (IllegalArgumentException e) {
            throw new NoSuchAttributeException(categoryName);
        }
        return carPartTypeRepository
                .findCarPartTypeByCarPartCategory(CategoryType.valueOf(categoryName))
                .stream()
                .map(carPartTypeMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        CarPartType carPartType = carPartTypeRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPartType.class, id));
        carPartTypeRepository.delete(carPartType);
    }
}
