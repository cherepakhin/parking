package ru.perm.v.parking.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.dto.CarDto;
import ru.perm.v.parking.service.CarService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarControllerTest {
    CarService carService = mock(CarService.class);

    @Test
    void newCar() {
        String gosNumber = "111";
        String model = "Lada-2109";

        CarEntity carEntity = new CarEntity();
        carEntity.setGosNumber(gosNumber);
        carEntity.setModel(model);

        CarController carController = new CarController(carService);

        when(carService.registration(gosNumber, model)).thenReturn(carEntity);

        CarDto actualCar = carController.newCar(gosNumber, model);

        CarDto expectedCar = new CarDto();
        expectedCar.setGosNumber(gosNumber);
        expectedCar.setModel(model);

        Assertions.assertEquals(expectedCar, actualCar);
    }

    @Test
    void deleteCar() {
        Long ID = 100L;
        CarEntity deletedCar = new CarEntity();
        deletedCar.setId(ID);
        when(carService.getById(ID)).thenReturn(deletedCar);
        when(carService.deleteById(ID)).thenReturn(true);
        CarController carController = new CarController(carService);

        ResponseEntity<String> ret = carController.deleteCar(ID);

        Assertions.assertEquals(HttpStatus.OK,ret.getStatusCode());
    }
}