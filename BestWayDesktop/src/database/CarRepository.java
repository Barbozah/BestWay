package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import models.Car;
import models.CarDefault;

public class CarRepository {
    private static CarRepository instance;
    private static Database db;

    private CarRepository() {
        db = SQLiteDB.getInstance();
    }

    public static CarRepository getInstance() {
        if (instance == null) {
            instance = new CarRepository();
        }
        return instance;
    }

    public void insertCar(Car car) {
        db.insert(
                new HashMap<String, String>() {
                    {
                        put("uuid %s", car.getUuid().toString());
                        put("model %s", car.getModel());
                        put("make %s", car.getMake());
                        put("color %s", car.getColor());
                        put("license_plate %s", car.getLicensePlate());
                        put("year %s", String.valueOf(car.getYear()));
                        put("seats %s", String.valueOf(car.getNumSeats()));
                    }
                },
                "car");
    }

    public Car insertCar(
            String make, String model, String color,
            String year, int seats, String licensePlate) {
        Car car = new CarDefault(
                make, model, color, year, seats, licensePlate);
        insertCar(car);
        return car;
    }

    public void deleteCar(String uuid) {
        db.delete("car", "uuid_car = " + uuid);
    }

    public Car getCar(String uuid) {
        List<Map<String, String>> result = db.select(
                "select * from car where uuid_car = " + uuid);
        if (result.size() == 0) {
            return null;
        }
        Map<String, String> row = result.get(0);
        return new CarDefault(
                row.get("uuid"),
                row.get("make"),
                row.get("model"),
                row.get("color"),
                row.get("year"),
                Integer.parseInt(row.get("seats")),
                row.get("license_plate"));
    }

    private Car parseCar(Map<String, String> row) {
        return new CarDefault(
                row.get("uuid"),
                row.get("make"),
                row.get("model"),
                row.get("color"),
                row.get("year"),
                Integer.parseInt(row.get("seats")),
                row.get("license_plate"));
    }

    public List<Car> getCarsByDriver(String uuid) {
        List<Map<String, String>> result = db.select(
                "select * from car " + 
                "left join driver on driver.uuid_car = car.uuid_car " +
                "where driver.uuid = " + uuid);
        List<Car> cars = new ArrayList<Car>();
        for (Map<String, String> row : result) {
            cars.add(parseCar(row));
        }
        return cars;
    }

    public List<Car> getAllCars() {
        List<Map<String, String>> result = db.select("select * from car");
        List<Car> cars = new ArrayList<Car>();
        for (Map<String, String> row : result) {
            cars.add(parseCar(row));
        }
        return cars;
    }
}
