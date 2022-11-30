package controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import database.CarRepository;
import database.UserRepository;
import models.Car;
import models.Driver;
import models.Passenger;
import models.User;

public class UserController {

  public void registerPassenger(
    String name, String email, String password, String address, 
    String phoneNumber, String paymentMethod, Double wallet
  ) {
    UserRepository.getInstance().insertPassenger(
      name, email, password, address, phoneNumber, paymentMethod, wallet
    );
  }

  public void registerDriver(
    String name, String email, String password, String address, 
    String phoneNumber, String uuid_car
  ) {
    Car car = CarRepository.getInstance().getCar(uuid_car);
    UserRepository.getInstance().insertDriver(
      name, email, password, address, phoneNumber, car
    );
  }

  public boolean loginUser(String email, String password) {
    User u = UserRepository.getInstance().getUser(email, password);
    if (u == null) {
      return false;
    }
    return true;
  }

  public void editUser(Map<String, String> fields) {
    UserRepository.getInstance().updateUser(fields);
  }

  public void deleteUser(String uuid) {
    UserRepository.getInstance().deleteUser(uuid);
  }

  public User searchUser(String uuid) {
    return UserRepository.getInstance().getUser(uuid);
  }

  public List<User> listUsers() {
    return UserRepository.getInstance().getAllUsers();
  }

  public List<Driver> listDrivers() {
    List<User> users = UserRepository.getInstance().getAllUsers();
    return users.stream()
      .filter(u -> u instanceof Driver)
      .map(u -> (Driver) u)
      .collect(Collectors.toList());
  }

  public void listPassengers() {
    List<User> users = UserRepository.getInstance().getAllUsers();
    users.stream()
      .filter(u -> u instanceof Passenger)
      .map(u -> (Passenger) u)
      .collect(Collectors.toList());
  }
}
