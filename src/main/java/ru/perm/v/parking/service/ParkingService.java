package ru.perm.v.parking.service;

import ru.perm.v.parking.db.ParkingEntity;

import java.util.List;

public interface ParkingService {
    List<ParkingEntity> getAll();
}
