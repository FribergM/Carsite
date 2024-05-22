package se.iths.friberg.carsite.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.iths.friberg.carsite.db.Car;
import se.iths.friberg.carsite.services.CarService;

@Controller
public class CarController{
    @Autowired
    CarService carService;

    @GetMapping("carReg")
    public String baseSite(Model m){
        m.addAttribute("regged","");
        return "registerCar";
    }
    @PostMapping("carReg")
    public String registration(@RequestParam String regNr,
                               @RequestParam String model,
                               @RequestParam int year,
                               Model m){

        String reg = carService.register(new Car(regNr,model,year));
        m.addAttribute("regged",reg);

        return "registerCar";
    }
    @GetMapping("all")
    public String allCars(Model m){
        m.addAttribute("carlist", carService.getAllCars());
        return "showallcars";
    }
}
