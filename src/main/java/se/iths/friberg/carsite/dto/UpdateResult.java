package se.iths.friberg.carsite.dto;

import se.iths.friberg.carsite.db.Car;

public class UpdateResult{
    public boolean success;
    public String statusMessage;
    public Car car;

    public UpdateResult(boolean success, String statusMessage){
        this.success = success;
        this.statusMessage = statusMessage;

    }
    public UpdateResult(boolean success, Car car){
        this.success = success;
        this.car = car;
    }
}
