package ru.perm.v.parking.service.impl;

import org.springframework.stereotype.Service;
import ru.perm.v.parking.db.CarEntity;
import ru.perm.v.parking.repository.CarRepository;
import ru.perm.v.parking.service.CarService;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarEntity registration(String gosNumber, String model) {
        CarEntity carEntity = new CarEntity();
        carEntity.setGosNumber(gosNumber);
        carEntity.setModel(model);
        carEntity = carRepository.save(carEntity);
        return carEntity;
    }

    @Override
    public CarEntity getById(Long id) {
        return carRepository.getOne(id);
    }

    @Override
    public List<CarEntity> getAll() {
        return carRepository.findAll();
    }

    @Override
    public Boolean deleteById(Long id) {
        CarEntity car = getById(id);
        if (car != null) {
            carRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
