package se.iths.friberg.carsite.db;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Car{
    @Id
    String regNr;
    @Column(nullable = false)
    String model;
    @Column(nullable = false)
    int year;

    public Car(String regNr, String model, int year){
        this.regNr = regNr;
        this.model = model;
        this.year = year;
    }
    public Car(){

    }

    public String getRegNr(){
        return regNr;
    }

    public String getModel(){
        return model;
    }

    public int getYear(){
        return year;
    }

    public void setRegNr(String regNr){
        this.regNr = regNr;
    }

    public void setModel(String model){
        this.model = model;
    }

    public void setYear(int year){
        this.year = year;
    }

    @Override
    public String toString(){
        return "Car{" +
                "regNr='" + regNr + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                '}';
    }
}
