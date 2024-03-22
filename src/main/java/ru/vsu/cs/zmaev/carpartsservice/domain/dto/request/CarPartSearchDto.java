package ru.vsu.cs.zmaev.carpartsservice.domain.dto.request;

import lombok.Data;

@Data
public class CarPartSearchDto {
    private Long manufacturerId;
    private String carPartTypeName;
    private String name;
    private String oem;
    private Double lastPrice;
    private String description;

}