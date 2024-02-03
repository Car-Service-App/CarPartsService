package ru.vsu.cs.zmaev.carpartsservice.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import ru.vsu.cs.zmaev.carpartsservice.domain.enums.CategoryType;

@Data
@Schema(description = "Описание класса CarPartCategoryRequestDto")
public class CarPartCategoryRequestDto {
    @Schema(description = "Название категории", example = "FILTER")
    private final CategoryType categoryName;
    @Schema(description = "Ссылка на бакет с изображением", example = "FILTER")
    private final String image;
}
