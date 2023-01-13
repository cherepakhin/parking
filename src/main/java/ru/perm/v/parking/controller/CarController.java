package ru.perm.v.parking.controller;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.parking.controller.exception.CarErrorMessage;
import ru.perm.v.parking.controller.exception.Error502;
import ru.perm.v.parking.controller.exception.Error503;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.dto.CarDto;
import ru.perm.v.parking.service.CarService;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST контроллер для добавления, удаления, изменения, получения машин
 */
@RestController
@RequestMapping("/car")
@Api(tags = "car-api")
@Slf4j
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/")
    @ApiOperation("Регистрация машины")
    public CarDto newCar(
            @ApiParam(
                    name = "gosNumber",
                    type = "String",
                    value = "Гос.номер машины",
                    example = "x122ab-59",
                    required = true)
            @RequestParam("gosNumber") String gosNumber,
            @ApiParam(
                    name = "model",
                    type = "String",
                    value = "Модель машины",
                    example = "ВАЗ-2109",
                    required = true)
            @RequestParam("model") String model) {
        //TODO: Проверить, что гос.номер свободен, еще не зарегистрирован
        CarEntity carEntity = carService.registration(gosNumber, model);
        return new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getModel());
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение машины по id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 502, message = "Не найдена машина с запрашиваемым id"),
            @ApiResponse(code = 503, message = "Ошибка при поиске машины с запрашиваемым id"),
    }
    )
    public CarDto getCar(
            @ApiParam(
                    name = "id",
                    type = "Long",
                    value = "ID машины",
                    example = "1",
                    required = true)
            @PathVariable Long id) {
//        Вариант 1
//        Если entity не найдено,т.е. не оборачивать в try-catch, то сообщение об ошибке генерирует Spring
//
//        Ошибка будет выглядеть так:
//        HTTP/1.1 500
//        Connection: close
//        Content-Type: application/json
//        Date: Fri, 13 Jan 2023 08:06:33 GMT
//        Transfer-Encoding: chunked
//
//        {
//            "error": "Internal Server Error",
//             "message": "Unable to find ru.perm.v.parking.db.CarEntity with id 222",
//             "path": "/car/222",
//             "status": 500,
//             "timestamp": "2023-01-13T08:06:33.436+0000"
//        }

//        CarEntity carEntity = carService.getById(id);
//        CarDto dto = new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getModel());
//        return dto;


//        Вариант 2:
//        Сообщение об ошибке генерируем сами
//        Ошибка будет выглядеть так:
//
//        HTTP/1.1 502
//        Connection: keep-alive
//        Content-Type: application/json
//        Date: Fri, 13 Jan 2023 09:20:38 GMT
//        Keep-Alive: timeout=60
//        Transfer-Encoding: chunked
//
//        {
//            "error": "Bad Gateway",
//            "message": "Не найдена машина с id=3",
//            "path": "/car/3",
//            "status": 502,
//            "timestamp": "2023-01-13T09:20:38.381+0000"
//        }

        try {
            CarEntity carEntity = carService.getById(id);
            CarDto dto = new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getModel());
            return dto;
        } catch (EntityNotFoundException e) {
            throw new Error502(String.format(CarErrorMessage.CAR_NOT_FOUND, id));
        } catch (Exception e) {
            throw new Error503(String.format(CarErrorMessage.CAR_OTHER_ERROR, id, e.getMessage()));
        }
    }

    @GetMapping("/")
    public List<CarDto> getAll() {
        return carService.getAll().stream().map(carEntity -> new CarDto(carEntity.getId(),
                carEntity.getGosNumber(), carEntity.getModel())).collect(Collectors.toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id) {
        CarEntity car = carService.getById(id);
        if (car.getId() == null) {
            throw new Error502(String.format(CarErrorMessage.CAR_NOT_FOUND, id));
        }

        try {
            carService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            String err = String.format(CarErrorMessage.CAR_OTHER_ERROR, id);
            throw new Error502(err);
        }
    }
}
