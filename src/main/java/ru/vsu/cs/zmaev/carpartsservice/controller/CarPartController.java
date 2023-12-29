package ru.vsu.cs.zmaev.carpartsservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.carpartsservice.controller.api.CarPartApi;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartType;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartService;

@RestController
@RequestMapping("api/car-parts")
@RequiredArgsConstructor
public class CarPartController implements CarPartApi {
    private final CarPartService carPartService;

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CarPartResponseDto>> findAllWithFilters(
            @RequestParam(defaultValue = "0") @Min(value = 0) Integer pagePosition,
            @RequestParam(defaultValue = "10") @Min(value = 1) Integer pageSize,
            @RequestParam(required = false) Long manufacturerId,
            @RequestParam(required = false) Long carId,
            @RequestParam(required = false) CarPartType carPartType,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String oem,
            @RequestParam(required = false) Double lastPrice,
            @RequestParam(required = false) String description,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection
    ) {
        EntityPage entityPage =
                new EntityPage(pagePosition, pageSize, sortDirection, sortBy);
        CarPartCriteriaSearch criteriaSearch =
                new CarPartCriteriaSearch();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartService.findAllWithFilters(entityPage, criteriaSearch));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarPartResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartService.findOneById(id));
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<CarPartResponseDto> create(@Valid @RequestBody CarPartRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartService.save(requestDto));
    }

    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarPartResponseDto> update(@PathVariable Long id, @Valid @RequestBody CarPartRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartService.update(id, dto));
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        carPartService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
