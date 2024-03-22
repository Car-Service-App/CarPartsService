package ru.vsu.cs.zmaev.carpartsservice.domain.dto.request;

import lombok.Data;

@Data
public class CarPartAnalogSearchDto {
    private String analogOem;
    private String originalOem;
    private String price;
}
