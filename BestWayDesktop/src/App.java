import java.util.HashMap;

import database.Database;
import database.SQLiteDB;
import database.UserRepository;
import models.Car;
import models.CarDefault;
import models.Driver;
import models.PBEKeySpecEncryptor;
import models.Passenger;
import models.PasswordEncrypt;
import views.MainView;

public class App {

  public static void main(String[] args) throws Exception {

    Database db = SQLiteDB.getInstance();
    UserRepository userRepo = UserRepository.getInstance();
    
    // Passenger passenger = userRepo.insertPassenger(
    //     "Heloise Nina Novaes",
    //     "heloise@email.com",
    //     "123",
    //     "Avenida das Flores - 379, Novo Mundo, Várzea Grande - MT, Brasil, 278149-584",
    //     "(65) 2882-5014",
    //     "Dinheiro",
    //     100.0);

    // Passenger passenger = userRepo.insertPassenger(
    //       "Igor Novaes",
    //       "igor@email.com",
    //       "123",
    //       "Avenida das Flores - 379, Novo Mundo, Várzea Grande - MT, Brasil, 278149-584",
    //       "(65) 2882-5014",
    //       "Dinheiro",
    //       100.0);

    // Car car = new CarDefault(
    //     "Fiat",
    //     "Uno",
    //     "Branco",
    //     "2010",
    //     4,
    //     "MWU-8729");

    // db.insert(
    //     new HashMap<String, String>() {
    //       {
    //         put("uuid_car %s", car.getUuid().toString());
    //         put("make %s", car.getMake());
    //         put("model %s", car.getModel());
    //         put("year %s", car.getYear());
    //         put("license_plate %s", car.getLicensePlate());
    //         put("color %s", car.getColor());
    //         put("seats %s", String.valueOf(car.getNumSeats()));
    //       }
    //     },
    //     "car");

    // Driver driver = userRepo.insertDriver(
    //     "Eduarda Maria Flávia da Paz", "maria@email.com", "123",
    //     "Rua Arnando Peres - 227, Itaúna, São Gonçalo - RJ, Brasil, 24476-130",
    //     "(21) 98149-1547", car);

    new MainView();

    // System.out.println(userRepo.searchUsers("name", "Eduarda"));
    // String latLong1 = "-35.2802787,-8.1228656";
    // String latLong2 = "-35.28217503962572,-8.1149182";
    // System.out.println(RoutingService.getTravelInfo(latLong1,
    // latLong2).toString());

    // Travel travel = new TravelDefault(
    // driver, passenger,
    // "Avenida das Flores - 379, Novo Mundo, Várzea Grande - MT, Brasil,
    // 78149-584",
    // "Rua das Margaridas - 441, Parque do Lago, Várzea Grande - MT, Brasil,
    // 78120-838",
    // 9.0);

    // travel.setDepartureTime(java.time.LocalDateTime.now().toLocalTime().toString());
    // travel.setArrivalTime(java.time.LocalDateTime.now().plusMinutes(17).toLocalTime().toString());
    // travel.setNumSeats(1);
    // travel.setNumSeatsAvailable(car.getNumSeats());

    // UserRepository userRepository = UserRepository.getInstance();

    // userRepository.insertPassenger(passenger);
    // userRepository.insertDriver(driver);

    // List<User> result = userRepository.search("Eduarda");

    // insert passenger

    // db.insert(
    // new HashMap<String, String>() {
    // {
    // put("uuid %s", passenger.getUuid().toString());
    // put("name %s", passenger.getName());
    // put("email %s", passenger.getEmail());
    // put("password %s", passenger.getPassword());
    // put("address %s", passenger.getAddress());
    // put("phone %s", passenger.getPhoneNumber());
    // put("rating %s", String.valueOf(passenger.getRating()));
    // }
    // },
    // "user");

    // db.insert(
    // new HashMap<String, String>() {
    // {
    // put("uuid %s", passenger.getUuid().toString());
    // put("payment_method %s", passenger.getPaymentMethod());
    // put("wallet %s", String.valueOf(passenger.getWallet()));
    // }
    // },
    // "passenger");

    // // insert driver

    // db.insert(
    // new HashMap<String, String>() {
    // {
    // put("uuid %s", driver.getUuid().toString());
    // put("name %s", driver.getName());
    // put("email %s", driver.getEmail());
    // put("password %s", driver.getPassword());
    // put("address %s", driver.getAddress());
    // put("phone %s", driver.getPhoneNumber());
    // put("rating %s", String.valueOf(driver.getRating()));
    // }
    // },
    // "user");

    // db.insert(
    // new HashMap<String, String>() {
    // {
    // put("uuid %s", driver.getUuid().toString());
    // put("car %s", car.getUuid().toString());
    // }
    // },
    // "driver");

    // // insert travel

    // db.insert(
    // new HashMap<String, String>() {
    // {
    // put("uuid_travel %s", travel.getUuid().toString());
    // put("origin %s", travel.getOrigin());
    // put("destination %s", travel.getDestination());
    // put("departure_time %s", travel.getDepartureTime());
    // put("arrival_time %s", travel.getArrivalTime());
    // put("seats %s", String.valueOf(travel.getNumSeats()));
    // put("seats_available %s", String.valueOf(travel.getNumSeatsAvailable()));
    // put("price %s", String.valueOf(travel.getPrice()));
    // put("driver %s", travel.getDriver().toString());
    // put("passenger %s", travel.getPassenger().toString());
    // }
    // },
    // "travel");

    // Object object = db.select("select * from travel");

    // System.out.println(object.toString());
  }
}
