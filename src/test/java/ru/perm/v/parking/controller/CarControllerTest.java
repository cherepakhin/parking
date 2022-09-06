package ru.perm.v.parking.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.dto.CarDto;
import ru.perm.v.parking.service.CarService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CarControllerTest {
    CarService carService = mock(CarService.class);

    @Test
    void newCar() {
        String gosNumber = "111";
        String mark = "Lada-2109";

        CarEntity carEntity = new CarEntity();
        carEntity.setGosNumber(gosNumber);
        carEntity.setMark(mark);

        CarController carController = new CarController(carService);

        when(carService.registration(gosNumber, mark)).thenReturn(carEntity);

        CarDto actualCar = carController.newCar(gosNumber, mark);

        CarDto expectedCar = new CarDto();
        expectedCar.setNumber(gosNumber);
        expectedCar.setMark(mark);

        Assertions.assertEquals(expectedCar, actualCar);
    }
}