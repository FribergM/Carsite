package se.iths.friberg.carsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.iths.friberg.carsite.db.Car;
import se.iths.friberg.carsite.services.CarService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rs/cars")
public class CarRestController{

    CarService carService;

    @Autowired
    public CarRestController(CarService carService){
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody Car car){
        Car registeredCar = carService.restRegister(car);

        if(registeredCar == null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(car.getRegNr()+" is already registered!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredCar);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> allCars(){
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{regNr}")
    public ResponseEntity<Car> findByRegNr(@PathVariable("regNr") String regNr){
        Optional<Car> car = carService.findByRegNr(regNr);

        if(car.isPresent()){
            return ResponseEntity.ok(car.get());
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<Car>> findByModel(@PathVariable("model") String model){
        List<Car> cars = carService.findByModel(model);

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Car>> findByYear(@PathVariable("year") int year){
        List<Car> cars = carService.findByYear(year);

        return ResponseEntity.ok(cars);
    }

    @PutMapping("/{regNr}")
    public ResponseEntity<Car> updateCar(@PathVariable("regNr") String regNr,
                                       @RequestBody Car updatedCar){

        if(!regNr.equals(updatedCar.getRegNr())){
            return ResponseEntity.badRequest().build();
        }

        Car car = carService.updateCar(regNr, updatedCar);

        if(car == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(car);

    }

    @DeleteMapping("/{regNr}")
    public ResponseEntity<?> deleteCar(@PathVariable("regNr") String regNr){

        boolean carExists = carService.existsByRegNr(regNr);

        if(!carExists){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car with RegNr:"+regNr+" was not found.");
        }

        boolean deleted = carService.removeCar(regNr);

        if(deleted){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete car with RegNr: " + regNr);
        }
    }
}
