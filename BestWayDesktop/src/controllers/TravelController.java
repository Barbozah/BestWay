package controllers;

import java.util.List;

import database.TravelRepository;
import database.UserRepository;
import models.Driver;
import models.Passenger;
import models.Travel;

public class TravelController {
    public TravelController() {
    }

    public void createTravel(String uuidDriver, String uuidCar, String uuidPassenger, String origin, String destination, String date, String time, String price) {
        Passenger p = UserRepository.getInstance().getPassenger(uuidPassenger);
        Driver d = UserRepository.getInstance().getDriver(uuidDriver);
        TravelRepository.getInstance().insertTravel(
            d, p, origin, destination, Double.parseDouble(price));
    }

    public void deleteTravel(String uuid) {
        TravelRepository.getInstance().deleteTravel(uuid);
    }

    public Travel getTravel(String uuid) {
        return TravelRepository.getInstance().getTravel(uuid);
    }

    public List<Travel> getTravels() {
        return TravelRepository.getInstance().getAllTravels();
    }

    public List<Travel> getTravelsByUser(String uuid) {
        return TravelRepository.getInstance().getTravelsByUser(uuid);
    }
}
