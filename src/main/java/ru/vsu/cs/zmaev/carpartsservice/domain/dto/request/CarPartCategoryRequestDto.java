package ru.vsu.cs.zmaev.carpartsservice.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класса CarPartCategoryRequestDto")
public class CarPartCategoryRequestDto {
    @Schema(description = "Название категории", example = "FILTER")
    private final String name;
}
