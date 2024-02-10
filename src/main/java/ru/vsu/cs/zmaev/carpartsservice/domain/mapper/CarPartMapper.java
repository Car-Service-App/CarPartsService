package ru.vsu.cs.zmaev.carpartsservice.domain.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartResponseForMarketplaceDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPart;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface CarPartMapper extends EntityMapper<CarPart, CarPartRequestDto, CarPartResponseDto> {
    @Override
    @Mapping(target = "carId", ignore = true)
    @Mapping(target = "manufacturerId", ignore = true)
    CarPart toEntity(CarPartRequestDto request);

    @Override
    @Mapping(source = "lastPrice", target = "price", qualifiedByName = "bigDecimalToDouble")
    CarPartResponseDto toDto(CarPart entity);

    @Mapping(source = "carPartType.partType", target = "carPartType")
    @Mapping(source = "lastPrice", target = "price", qualifiedByName = "bigDecimalToDouble")
    CarPartResponseForMarketplaceDto mapToCarPartResponseForMarketplaceDto(CarPart carPart);

    @Named("bigDecimalToDouble")
    default Double bigDecimalToDouble(BigDecimal value) {
        return value != null ? value.doubleValue() : null;
    }


}
