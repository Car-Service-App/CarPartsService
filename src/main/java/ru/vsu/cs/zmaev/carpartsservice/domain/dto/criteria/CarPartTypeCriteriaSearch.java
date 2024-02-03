package ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класса CarPartTypeCriteriaSearch")
public class CarPartTypeCriteriaSearch {
    @Schema(description = "Id детали", example = "Civic")
    private final Long id;
    @Schema(description = "Тип детали", example = "Civic")
    private final String partType;
    @Schema(description = "Категория детали", example = "Civic")
    private final String carPartCategory;
}
