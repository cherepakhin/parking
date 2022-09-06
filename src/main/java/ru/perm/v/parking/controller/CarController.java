package ru.perm.v.parking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.dto.CarDto;
import ru.perm.v.parking.service.CarService;

/**
 * REST контроллер для добавления, удаления, изменения машин
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
}
