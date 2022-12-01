package models;

public interface Driver extends User {
  public void setCar(Car car);

  public Car getCar();

  public double getWallet();

  public void setWallet(double amount);

  public static String getSQLString() {
    return "driver (uuid UUID, uuid_car UUID, wallet DOUBLE, PRIMARY KEY (uuid),FOREIGN KEY (uuid) REFERENCES user(uuid),FOREIGN KEY (uuid_car) REFERENCES car(uuid_car))";
  }
}