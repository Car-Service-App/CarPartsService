package ru.vsu.cs.zmaev.carpartsservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseForMarketplaceDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.IdResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartType;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.PartType;
import ru.vsu.cs.zmaev.carpartsservice.domain.mapper.CarPartMapper;
import ru.vsu.cs.zmaev.carpartsservice.exception.NoSuchEntityException;
import ru.vsu.cs.zmaev.carpartsservice.repository.criteria.CriteriaRepository;
import ru.vsu.cs.zmaev.carpartsservice.repository.jpa.CarPartRepository;
import ru.vsu.cs.zmaev.carpartsservice.repository.jpa.CarPartTypeRepository;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartService;

import java.math.BigDecimal;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarPartServiceImpl implements CarPartService {

    private final CarPartRepository carPartRepository;
    private final CriteriaRepository<CarPart, CarPartCriteriaSearch> carPartSearchCriteriaRepository;
    private final CarPartMapper carPartMapper;

    private final CarPartTypeRepository carPartTypeRepository;

    private final WebClient webClient;

    @Value("${car.service.base-url}")
    private String carServiceBaseUrl;

    @Override
    @Transactional(readOnly = true)
    public Page<CarPartResponseDto> findAllWithFilters(EntityPage entityPage, CarPartCriteriaSearch carPartCriteriaSearch) {
        return carPartSearchCriteriaRepository.findAllWithFilters(entityPage, carPartCriteriaSearch).map(carPartMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public CarPartResponseDto findOneById(Long id) {
        CarPart carPart = carPartRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPart.class, id));
        return carPartMapper.toDto(carPart);
    }

    @Transactional(readOnly = true)
    public CarPartResponseForMarketplaceDto findByIdWithStringType(Long id) {
        CarPart carPart = carPartRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPart.class, id));
        return carPartMapper.mapToCarPartResponseForMarketplaceDto(carPart);
    }

    @Override
    public CarPartResponseDto findOneByOem(String oem) {
        CarPart carPart = carPartRepository.findCarPartByOem(oem).orElseThrow(() ->
                new NoSuchEntityException("No such car part with oem: " + oem));
        return carPartMapper.toDto(carPart);
    }

    @Override
    @Transactional
    public CarPartResponseDto save(CarPartRequestDto carPartRequestDto) {
        CarPartType carPartType = carPartTypeRepository.findCarPartTypeByPartType(
                PartType.valueOf(carPartRequestDto.getCarPartTypeName())
        ).orElseThrow(() -> new NoSuchEntityException(CarPartType.class, carPartRequestDto.getCarPartTypeName()));
        CarPart carPart = carPartMapper.toEntity(carPartRequestDto);
        carPart.setCarId(findCarByIdSync(carPartRequestDto.getCarId()).getId());
        carPart.setManufacturerId(findManufacturerByIdSync(carPartRequestDto.getManufacturerId()).getId());
        carPart.setCarPartType(carPartType);
        carPart.setLastPrice(BigDecimal.valueOf(carPartRequestDto.getPrice()));
        carPart = carPartRepository.save(carPart);
        carPart.setCarPartType(carPartType);
        return carPartMapper.toDto(carPart);
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) {
        CarPart carPart = carPartRepository.findById(id).orElseThrow(() ->
                new NoSuchEntityException(CarPart.class, id));
        carPartRepository.delete(carPart);
    }

    private IdResponseDto findCarByIdSync(final Long id) {
        return webClient
                .get()
                .uri(String.format("%s/api/cars/{id}", carServiceBaseUrl), id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new NoSuchEntityException(IdResponseDto.class, id));
                    } else {
                        return Mono.error(new RuntimeException("Client error"));
                    }
                })
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException("Server error")))
                .bodyToMono(IdResponseDto.class)
                .block();
    }

    private IdResponseDto findManufacturerByIdSync(final Long id) {
        return webClient
                .get()
                .uri(String.format("%s/api/manufacturers/{id}", carServiceBaseUrl), id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new NoSuchEntityException(IdResponseDto.class, id));
                    } else {
                        return Mono.error(new RuntimeException("Client error"));
                    }
                })
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException("Server error")))
                .bodyToMono(IdResponseDto.class)
                .block();
    }
}
