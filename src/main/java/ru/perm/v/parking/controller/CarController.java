package ru.perm.v.parking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.dto.CarDto;
import ru.perm.v.parking.service.CarService;

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
    public ResponseEntity getCar(@PathVariable Long id) {
        try {
            CarEntity carEntity = carService.getById(id);
            System.out.println(carEntity);
            CarDto dto = new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getModel());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
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
        if(car.getId() == null) {
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
