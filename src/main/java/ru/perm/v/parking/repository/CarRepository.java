package ru.perm.v.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.perm.v.parking.db.CarEntity;

@Repository
@Transactional
public interface CarRepository extends JpaRepository<CarEntity, Long> {
}
