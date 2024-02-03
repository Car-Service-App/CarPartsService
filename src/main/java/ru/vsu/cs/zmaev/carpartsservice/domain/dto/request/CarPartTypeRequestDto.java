package ru.vsu.cs.zmaev.carpartsservice.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.PartType;

@Data
@Schema(description = "Описание класса CarPartTypeRequestDto")
public class CarPartTypeRequestDto {
    @Schema(description = "Тип детали", example = "OIL_FILTER")
    private final PartType partType;
    private final Long carPartCategoryId;
}
