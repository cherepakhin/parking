package ru.perm.v.parking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.parking.dto.CarDto;

/**
 * REST контроллер для добавления, удаления, изменения машин
 */
@RestController
@RequestMapping("/car")
public class CarController {

    public CarDto newCar(String gosNumber, String model) {
        return null;
    }
}
