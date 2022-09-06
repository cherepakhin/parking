package ru.perm.v.parking.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.repository.CarRepository;
import ru.perm.v.parking.service.CarService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

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
}