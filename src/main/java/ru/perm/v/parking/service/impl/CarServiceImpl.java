package ru.perm.v.parking.service.impl;

import org.springframework.stereotype.Service;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.repository.CarRepository;
import ru.perm.v.parking.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarEntity registration(String gosNumber, String mark) {
        CarEntity carEntity = new CarEntity();
        carEntity.setGosNumber(gosNumber);
        carEntity.setMark(mark);
        carEntity = carRepository.save(carEntity);
        return carEntity;
    }
}
