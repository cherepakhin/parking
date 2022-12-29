package ru.perm.v.parking.dto;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

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

    public ParkingDto(Long id, String address) {
        this.id = id;
        this.address = address;
    }
}
