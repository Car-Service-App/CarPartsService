package ru.vsu.cs.zmaev.carpartsservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.vsu.cs.zmaev.carpartsservice.controller.api.CarPartAnalogApi;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartAnalogRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartAnalogResponseDto;

@RestController
@RequestMapping("api/car-part-analogs")
public class CarPartAnalogController implements CarPartAnalogApi {

    @Override
    @GetMapping
    public ResponseEntity<CarPartAnalogResponseDto> findOneById(Long id) {
        return null;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Page<CarPartAnalogResponseDto>> findAllWithFilters(Integer pagePosition, Integer pageSize, String sortBy, Sort.Direction sortDirection) {
        return null;
    }

    @Override
    @PostMapping
    public ResponseEntity<CarPartAnalogResponseDto> create(CarPartAnalogRequestDto dto) {
        return null;
    }

    @Override
    @PatchMapping("/{id}")
    public ResponseEntity<CarPartAnalogResponseDto> update(Long id, CarPartAnalogRequestDto dto) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(Long id) {
        return null;
    }
}
