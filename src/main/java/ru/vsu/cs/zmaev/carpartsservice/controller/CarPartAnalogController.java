package ru.vsu.cs.zmaev.carpartsservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.carpartsservice.controller.api.CarPartAnalogApi;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartAnalogCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartAnalogRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartAnalogResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartAnalogService;

@RestController
@RequestMapping("api/car-part-analogs")
@RequiredArgsConstructor
public class CarPartAnalogController implements CarPartAnalogApi {

    private final CarPartAnalogService carPartAnalogService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CarPartAnalogResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartAnalogService.findOneById(id));
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<CarPartAnalogResponseDto>> findAllWithFilters(
            Integer pagePosition,
            Integer pageSize,
            String analogOem,
            String originalOem,
            String price,
            String sortBy,
            Sort.Direction sortDirection) {
        EntityPage entityPage =
                new EntityPage(pagePosition, pageSize, sortDirection, sortBy);
        CarPartAnalogCriteriaSearch carPartAnalogCriteriaSearch = new CarPartAnalogCriteriaSearch(
                0L,
                analogOem,
                originalOem,
                price);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartAnalogService.findAllWithFilters(entityPage, carPartAnalogCriteriaSearch));
    }


    @Override
    @PostMapping
    public ResponseEntity<CarPartAnalogResponseDto> create(@Valid @RequestBody CarPartAnalogRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartAnalogService.save(dto));
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<CarPartAnalogResponseDto> update(@PathVariable Long id, @Valid @RequestBody CarPartAnalogRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartAnalogService.update(id, dto));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        carPartAnalogService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
