package ru.vsu.cs.zmaev.carpartsservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCategoryCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartCategoryRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartCategoryResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.mapper.CarPartCategoryMapper;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartCategoryService;

@RestController
@RequestMapping("api/car-part-categories")
@RequiredArgsConstructor
public class CarPartCategoryController {
    private final CarPartCategoryService carPartCategoryService;
    private final CarPartCategoryMapper carPartCategoryMapper;

    @GetMapping
    public ResponseEntity<Page<CarPartCategoryResponseDto>> findAll(
            @RequestParam(defaultValue = "0") @Min(value = 0) Integer pagePosition,
            @RequestParam(defaultValue = "10") @Min(value = 1) Integer pageSize,
            @RequestParam(required = false) String name,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {
        EntityPage entityPage = new EntityPage(pagePosition, pageSize, sortDirection, sortBy);
        CarPartCategoryCriteriaSearch carPartCategoryCriteriaSearch =
                new CarPartCategoryCriteriaSearch(0L, name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartCategoryService.findAllWithFilters(entityPage, carPartCategoryCriteriaSearch));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarPartCategoryResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartCategoryMapper.toDto(carPartCategoryService.findOneById(id)));
    }

    @PostMapping
    public ResponseEntity<CarPartCategoryResponseDto> create(
            @Valid @RequestBody CarPartCategoryRequestDto carPartCategoryRequestDto) {
        CarPartCategoryResponseDto carPartCategoryResponseDto = carPartCategoryMapper
                .toDto(carPartCategoryService.save(carPartCategoryRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(carPartCategoryResponseDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarPartCategoryResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CarPartCategoryRequestDto carPartCategoryRequestDto) {
        CarPartCategoryResponseDto carPartCategoryResponseDto = carPartCategoryMapper
                .toDto(carPartCategoryService.update(id, carPartCategoryRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(carPartCategoryResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarPartCategoryResponseDto> delete(@PathVariable Long id) {
        carPartCategoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
