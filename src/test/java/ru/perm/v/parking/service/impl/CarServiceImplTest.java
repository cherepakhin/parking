package ru.perm.v.parking.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.repository.CarRepository;
import ru.perm.v.parking.service.CarService;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

class CarServiceImplTest {

    @Test
    void registration() {
        CarRepository carRepository = mock(CarRepository.class);
        CarService carService = new CarServiceImpl(carRepository);
        String gosNumber = "111";
        String mark = "Lada-2109";
        carService.registration(gosNumber, mark);
        CarEntity savedCar = new CarEntity();
        savedCar.setGosNumber(gosNumber);
        savedCar.setMark(mark);
        Mockito.verify(carRepository, times(1)).save(savedCar);
    }

    @Test
    void getById() {
        CarRepository carRepository = mock(CarRepository.class);
        CarService carService = new CarServiceImpl(carRepository);
        Long CAR_ID = 100L;
        CarEntity carEntity = new CarEntity();
        carEntity.setId(CAR_ID);
        when(carRepository.getOne(CAR_ID)).thenReturn(carEntity);
        CarEntity carActual = carService.getById(CAR_ID);
        Assertions.assertEquals(CAR_ID, carActual.getId());
        Mockito.verify(carRepository, times(1)).getOne(CAR_ID);
    }
}