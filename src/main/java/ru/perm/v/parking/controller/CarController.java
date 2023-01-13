package ru.perm.v.parking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.parking.controller.exception.Error502;
import ru.perm.v.parking.controller.exception.ErrorMessaage;
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
public class CarController {

    private final CarService carService;
    ObjectMapper mapper = new ObjectMapper();

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/")
    public CarDto newCar(@RequestParam("gosNumber") String gosNumber, @RequestParam("model") String model) {
        CarEntity carEntity = carService.registration(gosNumber, model);
        return new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getModel());
    }

    @GetMapping("/{id}")
    @ApiOperation("Получение машины по id")
    public CarDto getCar(@PathVariable Long id) {
//        Вариант 1
//        Если entity не найдено,то сообщение об ошибке генерирует Spring
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
            String err = String.format(ErrorMessaage.CAR_NOT_FOUND, id);
            throw new Error502(err);
        } catch (Exception e) {
            String err = String.format(ErrorMessaage.CAR_OTHER_ERROR, id);
            throw new Error502(err);
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
        System.out.println(car.getId());
        if (car.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not exist");
        }

        try {
            carService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("id not exist");
        }
    }
}
