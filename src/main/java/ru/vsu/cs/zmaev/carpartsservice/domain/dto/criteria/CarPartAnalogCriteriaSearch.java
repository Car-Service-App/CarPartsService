package ru.vsu.cs.zmaev.carpartsservice.domain.dto.criteria;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Описание класа CarPartAnalogCriteriaSearch")
public class CarPartAnalogCriteriaSearch {
    @Schema(description = "Id детали аналога")
    private final Long carPartAnalogId;
    @Schema(description = "OEM детали аналога")
    private final String analogOem;
    @Schema(description = "EOM оригинальной детали")
    private final String originalOem;
    @Schema(description = "Цена детали аналога")
    private final String price;
}
