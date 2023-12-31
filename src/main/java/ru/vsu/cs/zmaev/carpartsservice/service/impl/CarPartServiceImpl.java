package ru.vsu.cs.zmaev.carpartsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;
import ru.vsu.cs.zmaev.carpartsservice.domain.mapper.CarPartMapper;
import ru.vsu.cs.zmaev.carpartsservice.exception.NoSuchEntityException;
import ru.vsu.cs.zmaev.carpartsservice.repository.jpa.CarPartRepository;
import ru.vsu.cs.zmaev.carpartsservice.repository.criteria.CriteriaRepository;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartService;

@Service
@RequiredArgsConstructor
public class CarPartServiceImpl implements CarPartService {

    private final CarPartRepository carPartRepository;
    private final CriteriaRepository<CarPart, CarPartCriteriaSearch> carPartSearchCriteriaRepository;
    private final CarPartMapper carPartMapper;

    @Override
    public Page<CarPartResponseDto> findAllWithFilters(EntityPage entityPage, CarPartCriteriaSearch carPartCriteriaSearch) {
        return carPartSearchCriteriaRepository.findAllWithFilters(entityPage, carPartCriteriaSearch).map(carPartMapper::toDto);
    }

    @Override
    public CarPartResponseDto findOneById(Long id) {
        CarPart carPart = carPartRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPart.class, id));
        return carPartMapper.toDto(carPart);
    }

    @Override
    public CarPartResponseDto save(CarPartRequestDto carPartRequestDto) {
        CarPart carPart = carPartMapper.toEntity(carPartRequestDto);
        return carPartMapper.toDto(carPart);
    }

    @Override
    public CarPartResponseDto update(Long id, CarPartRequestDto carPartRequestDto) {
        return carPartRepository
                .findById(id)
                .map(existingEvent -> {
                    carPartMapper.partialUpdate(existingEvent, carPartRequestDto);
                    return existingEvent;
                })
                .map(carPartRepository::save)
                .map(carPartMapper::toDto)
                .orElseThrow(() -> new NoSuchEntityException(CarPart.class, id));
    }

    @Override
    public void delete(Long id) {
        CarPart carPart = carPartRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPart.class, id));
        carPartRepository.delete(carPart);
    }
}
