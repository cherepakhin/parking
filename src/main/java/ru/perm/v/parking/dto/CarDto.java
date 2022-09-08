package ru.perm.v.parking.dto;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CarDto {
    private Long id;
    private String gosNumber = "";
    private String model = "";
}
