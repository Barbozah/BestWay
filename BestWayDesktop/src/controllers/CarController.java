package controllers;

import java.util.List;

import database.CarRepository;
import models.Car;

public class CarController {

    public Car registerCar(String make, String model, String color, String year, int seats, String licensePlate) {
        return CarRepository.getInstance().insertCar(make, model, color, year, seats, licensePlate);
    }

    public Car updateCar(Car car) {
        return CarRepository.getInstance().updateCar(car);
    }

    public void deleteCar(String uuid) {
        CarRepository.getInstance().deleteCar(uuid);
    }

    public void getCar(String uuid) {
        CarRepository.getInstance().getCar(uuid);
    }

    public List<Car> getCars() {
        return CarRepository.getInstance().getAllCars();
    }

    public void getCarsByDriver(String uuid) {
        CarRepository.getInstance().getCarsByDriver(uuid);
    }

}
