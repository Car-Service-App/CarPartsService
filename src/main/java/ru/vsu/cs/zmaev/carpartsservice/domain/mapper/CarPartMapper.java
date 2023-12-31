package ru.vsu.cs.zmaev.carpartsservice.domain.mapper;

import org.mapstruct.Mapper;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;

@Mapper(componentModel = "spring")
public interface CarPartMapper extends EntityMapper<CarPart, CarPartRequestDto, CarPartResponseDto> {
    @Override
    CarPart toEntity(CarPartRequestDto request);

    @Override
    CarPartResponseDto toDto(CarPart entity);
}
