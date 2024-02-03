package ru.vsu.cs.zmaev.carpartsservice.domain.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.CategoryType;

@Data
@Schema(description = "Описание класса CarPartCategoryResponseDto")
public class CarPartCategoryResponseDto {
    @Schema(description = "Id категории")
    private final Long id;

    @Schema(description = "Название категории", example = "FILTER")
    private final CategoryType categoryName;

    @Schema(description = "Ссылка на бакет с изображением")
    private final String image;
}
