package ru.perm.v.parking.db;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "parking")
public class ParkingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address = "";
    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "parking_car",
            joinColumns = @JoinColumn(name = "parking_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    Set<CarEntity> cars = new HashSet<>();
}
