package ru.vsu.cs.zmaev.carpartsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartAnalogCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartAnalogRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartAnalogResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartAnalog;
import ru.vsu.cs.zmaev.carpartsservice.domain.mapper.CarPartAnalogMapper;
import ru.vsu.cs.zmaev.carpartsservice.exception.NoSuchEntityException;
import ru.vsu.cs.zmaev.carpartsservice.repository.criteria.CriteriaRepository;
import ru.vsu.cs.zmaev.carpartsservice.repository.jpa.CarPartAnalogRepository;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartAnalogService;

@Service
@RequiredArgsConstructor
public class CarPartAnalogServiceImpl implements CarPartAnalogService {

    private final CarPartAnalogRepository carPartAnalogRepository;
    private final CriteriaRepository<CarPartAnalog, CarPartAnalogCriteriaSearch> carPartAnalogCriteriaRepository;
    private final CarPartAnalogMapper carPartAnalogMapper;

    @Override
    public Page<CarPartAnalogResponseDto> findAllWithFilters(EntityPage entityPage, CarPartAnalogCriteriaSearch carPartAnalogCriteriaSearch) {
        return carPartAnalogCriteriaRepository
                .findAllWithFilters(entityPage, carPartAnalogCriteriaSearch)
                .map(carPartAnalogMapper::toDto);
    }

    @Override
    public CarPartAnalogResponseDto findOneById(Long id) {
        CarPartAnalog carPartAnalog = carPartAnalogRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPartAnalog.class, id));
        return carPartAnalogMapper.toDto(carPartAnalog);
    }

    @Override
    public CarPartAnalogResponseDto save(CarPartAnalogRequestDto carPartAnalogRequestDto) {
        CarPartAnalog carPartAnalog = carPartAnalogMapper.toEntity(carPartAnalogRequestDto);
        carPartAnalog = carPartAnalogRepository.save(carPartAnalog);
        return carPartAnalogMapper.toDto(carPartAnalog);
    }

    @Override
    public CarPartAnalogResponseDto update(Long id, CarPartAnalogRequestDto carPartAnalogRequestDto) {
        return carPartAnalogRepository
                .findById(id)
                .map(existingEvent -> {
                    carPartAnalogMapper.partialUpdate(existingEvent, carPartAnalogRequestDto);
                    return existingEvent;
                })
                .map(carPartAnalogRepository::save)
                .map(carPartAnalogMapper::toDto)
                .orElseThrow(() -> new NoSuchEntityException(CarPartAnalog.class, id));
    }

    @Override
    public void delete(Long id) {
        CarPartAnalog carPartAnalog = carPartAnalogRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPartAnalog.class, id));
        carPartAnalogRepository.delete(carPartAnalog);
    }
}
