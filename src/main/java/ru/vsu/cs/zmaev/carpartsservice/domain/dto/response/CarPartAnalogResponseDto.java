package ru.vsu.cs.zmaev.carpartsservice.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класа CarPartAnalogResponseDto")
public class CarPartAnalogResponseDto {
    @Schema(description = "Id детали аналога")
    private final Long id;
    @Schema(description = "Оригинальная деталь")
    private final CarPartResponseDto carPartResponseDto;
    @Schema(description = "OEM детали аналога")
    private final String analogOem;
}
