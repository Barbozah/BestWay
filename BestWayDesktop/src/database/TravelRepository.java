package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Driver;
import models.Passenger;
import models.Travel;
import models.TravelDefault;

public class TravelRepository {
    private static TravelRepository instance;
    private static Database db;

    private TravelRepository() {
        db = SQLiteDB.getInstance();
    }

    public static TravelRepository getInstance() {
        if (instance == null) {
            instance = new TravelRepository();
        }

        return instance;
    }

    public void insertTravel(Travel travel) {
        db.insert(
                new HashMap<String, String>() {
                    {
                        put("uuid_travel %s", travel.getUuid().toString());
                        put("uuid_driver %s", travel.getDriver());
                        put("uuid_passenger %s", travel.getPassenger());
                        put("origin %s", travel.getOrigin());
                        put("destination %s", travel.getDestination());
                        put("price %s", String.valueOf(travel.getPrice()));
                        put("departure_time %s", travel.getDepartureTime());
                        put("arrival_time %s", travel.getArrivalTime());
                        put("seats %s", String.valueOf(travel.getNumSeats()));
                        put("seats_available %s", String.valueOf(travel.getNumSeatsAvailable()));
                        put("rating %s", String.valueOf(travel.getRating()));
                    }
                },
                "travel");
    }

    public void insertTravel(
            Driver driver, Passenger passenger, String origin,
            String destination, String departureTime, String arrivalTime, double price) {
        Travel travel = new TravelDefault(
                driver, passenger, origin, destination, price);
        travel.setDepartureTime(departureTime);
        travel.setArrivalTime(arrivalTime);
        insertTravel(travel);
    }

    public void deleteTravel(String uuid) {
        db.delete("travel", uuid);
    }

    private Travel parseTravel(Map<String, String> row) {
        Travel travel = new TravelDefault(
                row.get("uuid"),
                row.get("uuid_driver"),
                row.get("uuid_passenger"),
                row.get("origin"),
                row.get("destination"),
                row.get("departure_time"),
                row.get("arrival_time"),
                Integer.parseInt(row.get("seats")),
                Integer.parseInt(row.get("seats_available")),
                Double.parseDouble(row.get("price")));
        travel.setRating(Float.parseFloat(row.get("rating")));
        return travel;
    }

    public Travel getTravel(String uuid) {
        List<Map<String, String>> rows = db.select("select * from travel where uuid_travel = '" + uuid + "'");
        if (rows.size() == 0) {
            return null;
        }
        Map<String, String> row = rows.get(0);
        return parseTravel(row);
    }

    public List<Travel> getAllTravels() {
        List<Map<String, String>> result = db.select(
                "select * from travel");
        List<Travel> travels = new ArrayList<Travel>();
        for (Map<String, String> row : result) {
            travels.add(parseTravel(row));
        }

        return travels;
    }

    public List<Travel> getTravelsByUser(String uuid) {
        List<Map<String, String>> result = db.select(
                "select * from travel where uuid_driver = '" + uuid +
                        "' or uuid_passenger = '" + uuid + "'");
        List<Travel> travels = new ArrayList<Travel>();
        for (Map<String, String> row : result) {
            travels.add(parseTravel(row));
        }
        return travels;
    }

    public List<Travel> getTravelsByCar(String uuid_car) {
        List<Map<String, String>> result = db.select(
                "select * from travel " +
                        "left join driver on travel.driver = driver.uuid " +
                        "where driver.uuid_car = '" + uuid_car + "'");
        List<Travel> travels = new ArrayList<Travel>();
        for (Map<String, String> row : result) {
            travels.add(parseTravel(row));
        }
        return travels;
    }
}
