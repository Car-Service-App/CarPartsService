package ru.vsu.cs.zmaev.carpartsservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.repository.CarPartRepository;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartService;

@Service
@RequiredArgsConstructor
public class CarPartServiceImpl implements CarPartService {

    private final CarPartRepository carPartRepository;

    @Override
    public Page<CarPartResponseDto> findAllWithFilters(EntityPage entityPage, CarPartCriteriaSearch carPartCriteriaSearch) {
        return null;
    }

    @Override
    public CarPartResponseDto findOneById(Long id) {
        return null;
    }

    @Override
    public CarPartResponseDto save(CarPartRequestDto carPartRequestDto) {
        return null;
    }

    @Override
    public CarPartResponseDto update(Long id, CarPartRequestDto carPartRequestDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
