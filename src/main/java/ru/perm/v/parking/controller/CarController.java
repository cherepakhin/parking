package ru.perm.v.parking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public class CarController {

    ObjectMapper mapper = new ObjectMapper();
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/")
    public CarDto newCar(@RequestParam("gosNumber") String gosNumber, @RequestParam("model") String model) {
        CarEntity carEntity = carService.registration(gosNumber, model);
        return new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getModel());
    }

    @GetMapping("/{id}")
    public ResponseEntity getCar(@PathVariable Long id) {
        try {
            CarEntity carEntity=carService.getById(id);
            System.out.println(carEntity);
            CarDto dto = new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getModel());
            return new ResponseEntity<>(dto,HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public List<CarDto> getAll() {
        return carService.getAll().stream().map(carEntity -> new CarDto(carEntity.getId(),
                carEntity.getGosNumber(), carEntity.getModel())).collect(Collectors.toList());
    }

}
