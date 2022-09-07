package ru.perm.v.parking.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.perm.v.parking.dto.ParkingDto;
import ru.perm.v.parking.service.ParkingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    ObjectMapper mapper = new ObjectMapper();
    ParkingService parkingService;

    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @GetMapping("/")
    public List<ParkingDto> getAll() {
        return parkingService.getAll().stream()
                .map(parkingEntity -> new ParkingDto(parkingEntity.getId(), parkingEntity.getAddress()))
                .collect(Collectors.toList());
    }
}
