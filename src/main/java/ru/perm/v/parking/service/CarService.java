package ru.perm.v.parking.service;


import ru.perm.v.parking.db.CarEntity;

import java.util.List;

public interface CarService {
    CarEntity registration(String gosNumber, String mark);

    CarEntity getById(Long id);

    List<CarEntity> getAll();

    Boolean deleteById(Long id);
}
