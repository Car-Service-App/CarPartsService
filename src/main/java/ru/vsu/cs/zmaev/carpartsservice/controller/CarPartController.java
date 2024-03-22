package ru.vsu.cs.zmaev.carpartsservice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.carpartsservice.controller.api.CarPartApi;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.EntityPage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria.CarPartCriteriaSearch;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartSearchDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseForMarketplaceDto;
import ru.vsu.cs.zmaev.carpartsservice.service.impl.CarPartServiceImpl;

@Slf4j
@RestController
@RequestMapping("api/car-parts")
@RequiredArgsConstructor
public class CarPartController implements CarPartApi {

    private final CarPartServiceImpl carPartService;

    @PostMapping(value = "/filter", produces = "application/json")
    public ResponseEntity<Page<CarPartResponseDto>> findAllWithFilters(
            @RequestParam(defaultValue = "0") @Min(value = 0) Integer pagePosition,
            @RequestParam(defaultValue = "10") @Min(value = 1) Integer pageSize,
            @RequestBody CarPartSearchDto searchDto,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") Sort.Direction sortDirection
    ) {
        EntityPage entityPage = new EntityPage(pagePosition, pageSize, sortDirection, sortBy);
        CarPartCriteriaSearch criteriaSearch = new CarPartCriteriaSearch(
                0L,
                searchDto.getManufacturerId(),
                searchDto.getCarPartTypeName(),
                searchDto.getName(),
                searchDto.getOem(),
                searchDto.getLastPrice(),
                searchDto.getDescription()
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartService.findAllWithFilters(entityPage, criteriaSearch));
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Page<CarPartResponseDto>> findAll(
            @RequestParam(defaultValue = "0") @Min(0) Integer pagePosition,
            @RequestParam(defaultValue = "10") @Min(1) Integer pageSize
    ) {
        Page<CarPartResponseDto> carPartPage = carPartService.findAll(PageRequest.of(pagePosition, pageSize));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(carPartPage);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<CarPartResponseDto> findOneById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartService.findOneById(id));
    }

    @GetMapping(value = "/string-name/{id}", produces = "application/json")
    public ResponseEntity<CarPartResponseForMarketplaceDto> findOneByIdWithStringType(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartService.findByIdWithStringType(id));
    }

    @GetMapping(value = "/oem/{oem}", produces = "application/json")
    public ResponseEntity<CarPartResponseDto> findOneByOem(@PathVariable String oem) {
        return ResponseEntity.status(HttpStatus.OK).body(carPartService.findOneByOem(oem));
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
