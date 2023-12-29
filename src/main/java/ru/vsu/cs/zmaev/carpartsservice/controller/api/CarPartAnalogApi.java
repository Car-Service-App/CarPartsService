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
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.request.CarPartAnalogRequestDto;
import ru.vsu.cs.zmaev.carpartsservice.domain.dto.response.CarPartAnalogResponseDto;

@Tag(name = "CarPartAnalog Api", description = "Api для работы с аналогами запчастей")
public interface CarPartAnalogApi {
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат аналога детали автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartAnalogResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Аналога детали автомобиля по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение аналога детали по id")
    ResponseEntity<CarPartAnalogResponseDto> findOneById(@Parameter(description = "id автомобиля") Long id);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный возврат аналога деталей автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartAnalogResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Неверный тип аналога детали автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Получение всех аналогов детали автомобилей")
    ResponseEntity<Page<CarPartAnalogResponseDto>> findAllWithFilters(
            @Parameter(description = "Начальная страница")
            @RequestParam(defaultValue = "0") @Min(value = 0)
            Integer pagePosition,
            @Parameter(description = "Размер страницы")
            @RequestParam(defaultValue = "10") @Min(value = 1)
            Integer pageSize,
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
                    description = "Успешный создание аналога детали автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartAnalogResponseDto.class)
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
    @Operation(summary = "Создание аналога детали автомобиля")
    ResponseEntity<CarPartAnalogResponseDto> create(@Valid CarPartAnalogRequestDto dto);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешный создание аналога детали автомобиля",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CarPartAnalogResponseDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Аналога детали автомобиля по переданному id не существует",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)
                            )
                    }
            )
    })
    @Operation(summary = "Обновление аналога детали автомобиля по id")
    ResponseEntity<CarPartAnalogResponseDto> update(
            @Parameter(description = "id аналога детали автомобиля")
            Long id,
            @Parameter(description = "Dto запроса аналога детали автомобиля")
            @Valid
            CarPartAnalogRequestDto dto);

    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Успешное удаление автомобиля"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Аналога детали автомобиля по переданному id не существует",
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
    @Operation(summary = "Удаление аналога деталии автомобиля по id")
    ResponseEntity<Void> delete(@Parameter(description = "id аналога детали автомобиля") Long id);
}
