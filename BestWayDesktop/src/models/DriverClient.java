package models;

public class DriverClient extends UserWithHashedPassword implements Driver {

  private Car car;

  public DriverClient(String name, String email, String password, String address, String phoneNumber, Car car) {
    super(name, email, password, address, phoneNumber);
    this.car = car;
  }

  public void setCar(Car car) {
    this.car = car;
  }

  public Car getCar() {
    return this.car;
  }
}
