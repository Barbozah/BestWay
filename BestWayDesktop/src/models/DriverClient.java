package models;

import java.util.UUID;

public class DriverClient extends UserWithHashedPassword implements Driver {

  private Car car;
  private double wallet;

  public DriverClient(String name, String email, String password, String address, String phoneNumber, Car car) {
    super(name, email, password, address, phoneNumber);
    this.car = car;
    this.wallet = 0;
  }

  public DriverClient(String uuid, String salt, String name, String email, String password, String address,
      String phoneNumber, float rating, double wallet, Car car) {
    super(name, email, password, address, phoneNumber);
    this.uuid = UUID.fromString(uuid);
    this.salt = salt;
    this.password = password;
    this.car = car;
    this.wallet = wallet;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Car getCar() {
    return this.car;
  }

  public String toString() {
    return String.format(
        "Driver: %s, %s, %s, %s",
        this.getName(),
        this.car.getLicensePlate(),
        this.car.getModel(),
        this.car.getColor());
  }

  @Override
  public double getWallet() {
    return this.wallet;
  }

  @Override
  public void setWallet(double amount) {
    this.wallet = amount;
  }
}
