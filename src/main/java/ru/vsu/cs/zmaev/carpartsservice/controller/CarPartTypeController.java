package ru.vsu.cs.zmaev.carpartsservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.carpartsservice.controller.api.CarPartTypeApi;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartTypeCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartTypeRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartTypeResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartTypeService;

import java.util.List;

@RestController
@RequestMapping("api/car-part-type")
@RequiredArgsConstructor
public class CarPartTypeController implements CarPartTypeApi {
    private final CarPartTypeService carPartTypeService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CarPartTypeResponseDto>> findAllWithFilters(
            @RequestParam(defaultValue = "0") @Min(value = 0) Integer pagePosition,
            @RequestParam(defaultValue = "10") @Min(value = 1) Integer pageSize,
            @RequestParam(required = false) String partType,
            @RequestParam(required = false) String carPartCategory,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection
    ) {
        EntityPage entityPage =
                new EntityPage(pagePosition, pageSize, sortDirection, sortBy);
        CarPartTypeCriteriaSearch criteriaSearch =
                new CarPartTypeCriteriaSearch(0L, partType, carPartCategory);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartTypeService.findAllWithFilters(entityPage, criteriaSearch));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarPartTypeResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartTypeService.findOneById(id));
    }

    @GetMapping(value = "/category-name", produces = "application/json")
    public ResponseEntity<List<CarPartTypeResponseDto>> findCarPartTypesByCategoryName(
            @RequestParam String categoryName
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartTypeService.findCarPartTypeByCarPartCategory(categoryName));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<CarPartTypeResponseDto> create(@Valid @RequestBody CarPartTypeRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartTypeService.save(requestDto));
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarPartTypeResponseDto> update(@PathVariable Long id, @Valid @RequestBody CarPartTypeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartTypeService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carPartTypeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
