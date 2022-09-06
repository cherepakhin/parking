package ru.perm.v.parking.service;


import ru.perm.v.parking.db.CarEntity;

public interface CarService {
    CarEntity registration(String gosNumber, String mark);
}
