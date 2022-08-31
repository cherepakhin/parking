package ru.perm.v.parking.dto;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ParkingDto {
    private Long id;
    private String address = "";
    private List<CarDto> cars = new ArrayList<>();
}
