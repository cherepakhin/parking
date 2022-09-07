package ru.perm.v.parking.service.impl;

import org.springframework.stereotype.Service;
import ru.perm.v.parking.db.ParkingEntity;
import ru.perm.v.parking.repository.ParkingRepository;
import ru.perm.v.parking.service.ParkingService;

import java.util.List;

@Service
public class ParkingServiceImpl implements ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingServiceImpl(ParkingRepository repository) {
        this.parkingRepository = repository;
    }

    @Override
    public List<ParkingEntity> getAll() {
        return parkingRepository.findAll();
    }
}
