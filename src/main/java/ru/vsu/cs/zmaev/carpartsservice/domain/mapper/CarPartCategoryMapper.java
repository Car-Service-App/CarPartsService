package ru.vsu.cs.zmaev.carpartsservice.domain.mapper;

import org.mapstruct.Mapper;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartCategoryRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartCategoryResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;

@Mapper(componentModel = "spring")
public interface CarPartCategoryMapper extends EntityMapper<CarPartCategory, CarPartCategoryRequestDto, CarPartCategoryResponseDto> {

    CarPartCategory toEntity(CarPartCategoryRequestDto carPartCategoryRequestDto);

    CarPartCategoryResponseDto toDto(CarPartCategory carPartCategory);

    CarPartCategoryResponseDto toResponseDto(CarPartCategoryRequestDto carPartCategoryRequestDto);



}
