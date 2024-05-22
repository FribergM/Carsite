package se.iths.friberg.carsite.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, String>{
    boolean existsByRegNr(String regNr);
    List<Car> findByModel(String model);
    List<Car> findByYear(int year);
    void deleteByRegNr(String regNr);
}
