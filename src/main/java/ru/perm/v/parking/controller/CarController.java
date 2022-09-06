package ru.perm.v.parking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        return new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getMark());
    }

    @GetMapping("/{id}")
    public CarDto getCar(@PathVariable Long id) {
        CarEntity carEntity=carService.getById(id);
        return new CarDto(carEntity.getId(), carEntity.getGosNumber(), carEntity.getMark());
    }

    @GetMapping("/")
    public List<CarDto> getAll() {
        List<CarEntity> cars=carService.getAll();
        return cars.stream().map(carEntity -> new CarDto(carEntity.getId(),
                carEntity.getGosNumber(), carEntity.getMark())).collect(Collectors.toList());
    }

}
