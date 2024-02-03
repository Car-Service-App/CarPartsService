package ru.vsu.cs.zmaev.carpartsservice.domain.mapper;

import org.mapstruct.Mapper;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartTypeRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartTypeResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartType;

@Mapper(componentModel = "spring")
public interface CarPartTypeMapper extends EntityMapper<CarPartType, CarPartTypeRequestDto, CarPartTypeResponseDto> {
    CarPartType toEntity(CarPartTypeRequestDto carPartTypeRequestDto);
    CarPartTypeResponseDto toDto(CarPartType carPartType);
}
