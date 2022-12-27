package ru.perm.v.parking.dto;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * DTO машины
 */
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
    /**
     * Гос.номер
     */
    private String gosNumber = "";
    /**
     * Модель (ВАЗ-.. и т.п.)
     */
    private String model = "";
}
