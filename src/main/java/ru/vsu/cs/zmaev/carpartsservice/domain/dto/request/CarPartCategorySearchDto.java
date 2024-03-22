package ru.vsu.cs.zmaev.carpartsservice.domain.dto.request;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class CarPartCategorySearchDto {
    @Nullable
    private String categoryName;
}
