package ru.vsu.cs.zmaev.carpartsservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.carpartsservice.controller.api.CarPartAnalogApi;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartAnalogCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartAnalogRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartAnalogSearchDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartAnalogResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.service.CarPartAnalogService;

@RestController
@RequestMapping("api/car-part-analogs")
@RequiredArgsConstructor
public class CarPartAnalogController implements CarPartAnalogApi {

    private final CarPartAnalogService carPartAnalogService;

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarPartAnalogResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartAnalogService.findOneById(id));
    }

    @Override
    @PostMapping(value = "/filter", produces = "application/json")
    public ResponseEntity<Page<CarPartAnalogResponseDto>> findAllWithFilters(
            @RequestParam(defaultValue = "0") Integer pagePosition,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @Valid @RequestBody CarPartAnalogSearchDto searchDto,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection) {
        EntityPage entityPage = new EntityPage(pagePosition, pageSize, sortDirection, sortBy);
        CarPartAnalogCriteriaSearch carPartAnalogCriteriaSearch = new CarPartAnalogCriteriaSearch(
                0L,
                searchDto.getAnalogOem(),
                searchDto.getOriginalOem(),
                searchDto.getPrice());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartAnalogService.findAllWithFilters(entityPage, carPartAnalogCriteriaSearch));
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CarPartAnalogResponseDto>> findAll(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size) {
        Page<CarPartAnalogResponseDto> carPartAnalogPage = carPartAnalogService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(carPartAnalogPage);
    }

    @Override
    @PostMapping(produces = "application/json")
    public ResponseEntity<CarPartAnalogResponseDto> create(@Valid @RequestBody CarPartAnalogRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartAnalogService.save(dto));
    }

    @Override
    @PatchMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarPartAnalogResponseDto> update(@PathVariable Long id, @Valid @RequestBody CarPartAnalogRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartAnalogService.update(id, dto));
    }

    @Override
    @DeleteMapping(value =  "/{id}", produces = "application/json")
    public ResponseEntity<Void> delete(Long id) {
        carPartAnalogService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
