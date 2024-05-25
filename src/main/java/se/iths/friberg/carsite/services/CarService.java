package se.iths.friberg.carsite.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.iths.friberg.carsite.db.Car;
import se.iths.friberg.carsite.db.CarRepository;


import java.util.List;
import java.util.Optional;

@Service
public class CarService{

    @Autowired
    CarRepository carRepo;

    @Transactional
    public String register(Car car){
    if(carRepo.existsByRegNr(car.getRegNr())){
        return car.getRegNr()+" is already registered!";
    }
    car.setRegNr(car.getRegNr().toUpperCase());
        carRepo.save(car);
        return car.getRegNr()+" has been registered!";
    }
    
    @Transactional
    public Car restRegister(Car car){
        if(carRepo.existsByRegNr(car.getRegNr())){
            return null;
        }
        car.setRegNr(car.getRegNr().toUpperCase());
        carRepo.save(car);
        return car;
    }

    public boolean existsByRegNr(String regNr){
        return carRepo.existsByRegNr(regNr);
    }

    public List<Car> getAllCars(){
        return carRepo.findAll();
    }

    public Optional<Car> findByRegNr(String regNr){
        return carRepo.findById(regNr);
    }
    public List<Car> findByModel(String model){
        return carRepo.findByModel(model);
    }
    public List<Car> findByYear(int year){
        return carRepo.findByYear(year);
    }

    @Transactional
    public Car updateCar(String regNr, Car updatedCar){
        
        Car car = carRepo.findById(regNr).orElse(null);
        if(car == null){
            return null;
        }
        
        car.setModel(updatedCar.getModel());
        car.setYear(updatedCar.getYear());
        carRepo.save(car);

        return car;
    }

    @Transactional
    public boolean removeCar(String regNr){
        try{
            carRepo.deleteByRegNr(regNr);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
