package models;

public interface Driver extends User {
  public void setCar(Car car);

  public Car getCar();

  public static String getSQLString() {
    return "driver(uuid UUID, car UUID)";
  }
}