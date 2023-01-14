package ru.perm.v.parking.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.perm.v.parking.controller.exception.Error502;
import ru.perm.v.parking.controller.exception.Error503;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.dto.CarDto;
import ru.perm.v.parking.service.CarService;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.*;

class CarControllerTest {

    @Test
    void newCar() {
        String gosNumber = "111";
        String model = "Lada-2109";

        CarEntity carEntity = new CarEntity();
        carEntity.setGosNumber(gosNumber);
        carEntity.setModel(model);

        CarService carService = mock(CarService.class);
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
        CarService carService = mock(CarService.class);
        when(carService.getById(ID)).thenReturn(deletedCar);
        when(carService.deleteById(ID)).thenReturn(true);
        CarController carController = new CarController(carService);

        ResponseEntity<String> ret = carController.deleteCar(ID);

        verify(carService, times(1)).getById(ID);
        verify(carService, times(1)).deleteById(ID);
        Assertions.assertEquals(HttpStatus.OK, ret.getStatusCode());
    }

    @Test
    void getNotExistCar() {
        Long ID = 100L;
        CarEntity car = new CarEntity();
        car.setId(ID);
        CarService carService = mock(CarService.class);
        when(carService.getById(ID)).thenThrow(EntityNotFoundException.class);
        CarController carController = new CarController(carService);

//      Не сработает, т.к. замокан when(carService.getById(ID)) через thenThrow
//      verify(carService, times(1)).getById(any(Long.class));

        Assertions.assertThrows(Error502.class, () -> carController.getCar(ID));
    }

    @Test
    void getCarWithNormalID() {
        Long ID = 100L;
        CarEntity car = new CarEntity();
        car.setId(ID);
        CarService carService = mock(CarService.class);
        when(carService.getById(ID)).thenReturn(car);
        CarController carController = new CarController(carService);

        CarDto carDtoReceived = carController.getCar(ID);

        verify(carService, times(1)).getById(ID);
        Assertions.assertEquals(ID, carDtoReceived.getId());
    }

    @Test
    void getOtherErrorForGetCar() {
        Long ID = 100L;
        CarService carService = mock(CarService.class);

        CarController carController = new CarController(carService);

        Assertions.assertThrows(Error503.class, () -> carController.getCar(ID));
        verify(carService, times(1)).getById(ID);
    }
}