package ru.vsu.cs.zmaev.carpartsservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.carpartsservice.controller.api.CarPartCategoryApi;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCategoryCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartCategoryRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartCategorySearchDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartCategoryResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartCategoryService;

@RestController
@RequestMapping("api/car-part-categories")
@RequiredArgsConstructor
public class CarPartCategoryController implements CarPartCategoryApi {

    private final CarPartCategoryService carPartCategoryService;

    @PostMapping(value = "/filter", produces = "application/json")
    public ResponseEntity<Page<CarPartCategoryResponseDto>> findAllWithFilters(
            @RequestParam(defaultValue = "0") @Min(value = 0) Integer pagePosition,
            @RequestParam(defaultValue = "10") @Min(value = 1) Integer pageSize,
            @RequestBody CarPartCategorySearchDto carPartCategorySearchDto,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {
        EntityPage entityPage = new EntityPage(pagePosition, pageSize, sortDirection, sortBy);
        CarPartCategoryCriteriaSearch carPartCategoryCriteriaSearch =
                new CarPartCategoryCriteriaSearch(0L, carPartCategorySearchDto.getCategoryName());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartCategoryService.findAllWithFilters(entityPage, carPartCategoryCriteriaSearch));
    }

    @GetMapping( produces = "application/json")
    public ResponseEntity<Page<CarPartCategoryResponseDto>> findAll(
            @RequestParam(defaultValue = "0") @Min(value = 0) Integer pagePosition,
            @RequestParam(defaultValue = "10") @Min(value = 1) Integer pageSize
    ) {
        return ResponseEntity.ok(carPartCategoryService.findAll(PageRequest.of(pagePosition, pageSize)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarPartCategoryResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartCategoryService.findOneById(id));
    }

    @PostMapping
    public ResponseEntity<CarPartCategoryResponseDto> create(
            @Valid @RequestBody CarPartCategoryRequestDto carPartCategoryRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartCategoryService.save(carPartCategoryRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarPartCategoryResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody CarPartCategoryRequestDto carPartCategoryRequestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartCategoryService.update(id, carPartCategoryRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carPartCategoryService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
