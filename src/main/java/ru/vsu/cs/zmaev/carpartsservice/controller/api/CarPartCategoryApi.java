package ru.vsu.cs.zmaev.carpartsservice.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.ErrorMessage;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartCategoryRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartCategoryResponseDto;

@Tag(name = "CarPartCategory Api", description = "Api для работы с категориями запчастей")
public interface CarPartCategoryApi {
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат типов деталей автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartCategoryResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Типа детали автомобиля по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение автомобиля по id")
    ResponseEntity<CarPartCategoryResponseDto> findOneById(@Parameter(description = "id автомобиля") Long id);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат типа детали автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartCategoryResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный тип детали автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение всех типов деталей автомобилей")
    ResponseEntity<Page<CarPartCategoryResponseDto>> findAllWithFilters(
            @Parameter(description = "Начальная страница")
            @RequestParam(defaultValue = "0") @Min(value = 0)
            Integer pagePosition,
            @Parameter(description = "Размер страницы")
            @RequestParam(defaultValue = "10") @Min(value = 1)
            Integer pageSize,
            @Parameter(description = "Название категории") @RequestParam(required = false) String name,
            @RequestParam(required = false)
            @Parameter(description = "Поле для сортировки") String sortBy,
            @RequestParam(required = false)
            @Parameter(
                    in = ParameterIn.QUERY,
                    description = "Порядок сортировки",
                    name = "sortDirection",
                    schema = @Schema(allowableValues = {
                            "ASC",
                            "DESC"
                    }))
            Sort.Direction sortDirection);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный создание типа детали автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartCategoryResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный запрос",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Создание типа детали автомобиля")
    ResponseEntity<CarPartCategoryResponseDto> create(@Valid CarPartCategoryRequestDto dto);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный создание типа детали автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartCategoryResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Типа детали автомобиля по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Обновление типа детали автомобиля по id")
    ResponseEntity<CarPartCategoryResponseDto> update(
            @Parameter(description = "id типа детали автомобиля")
            Long id,
            @Parameter(description = "Dto запроса типа детали автомобиля")
            @Valid
            CarPartCategoryRequestDto dto);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успешное удаление автомобиля"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Типа детали автомобиля по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный запрос",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Удаление типа детали автомобиля по id")
    ResponseEntity<Void> delete(@Parameter(description = "id типа детали автомобиля") Long id);
}
