package ru.vsu.cs.zmaev.carpartsservice.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класа CarPartAnalogRequestDto")
public class CarPartAnalogRequestDto {
    @Schema(description = "Id детали аналога")
    private final Long id;
    @Schema(description = "Id оригинальной детали")
    private final Long carPartId;
    @Schema(description = "OEM детали аналога")
    private final String analogOem;
}
