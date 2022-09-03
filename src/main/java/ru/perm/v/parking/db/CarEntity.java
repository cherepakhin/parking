package ru.perm.v.parking.db;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "car")
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "gos_number")
    private String gosNumber = "";
    private String mark = "";
}
