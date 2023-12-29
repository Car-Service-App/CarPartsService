package ru.vsu.cs.zmaev.carpartsservice.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.vsu.cs.zmaev.carpartsservice.domain.entity.CarPartCategory;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.PartType;

@Data
@Schema(description = "Описание класса CarPartTypeResponseDto")
public class CarPartTypeResponseDto {
    @Schema(description = "Id детали")
    Long id;
    @Schema(description = "Тип детали", example = "Civic")
    private final PartType partType;
    @Schema(description = "Категория детали", example = "Civic")
    private CarPartCategory carPartCategory;
}
