package ru.vsu.cs.zmaev.carpartsservice.domain.mapper;

import org.mapstruct.Mapper;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartAnalogRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartAnalogResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartAnalog;

@Mapper(componentModel = "spring")
public interface CarPartAnalogMapper extends EntityMapper<CarPartAnalog, CarPartAnalogRequestDto, CarPartAnalogResponseDto> {
    @Override
    CarPartAnalog toEntity(CarPartAnalogRequestDto request);

    @Override
    CarPartAnalogResponseDto toDto(CarPartAnalog entity);
}
