package ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класса CarPartCategoryCriteriaSearch")
public class CarPartCategoryCriteriaSearch {
    @Schema(description = "Id категории")
    private final Long id;

    @Schema(description = "Название категории", example = "FILTER")
    private final String name;
}
