package models;

import java.util.UUID;

public interface Travel {
  public UUID getUuid();

  public String getOrigin();

  public String getDestination();

  public String getDepartureTime();

  public String getArrivalTime();

  public int getNumSeats();

  public int getNumSeatsAvailable();

  public void setNumSeatsAvailable(int numSeatsAvailable);

  public double getPrice();

  public void setPrice(double price);

  public Driver getDriver();

  public void setDriver(Driver driver);

  public Passenger getPassenger();

  public void setPassenger(Passenger passenger);

  public void setOrigin(String origin);

  public void setDestination(String destination);

  public void setDepartureTime(String departureTime);

  public void setArrivalTime(String arrivalTime);

  public void setNumSeats(int numSeats);

  public static String getSQLString() {
    return "travel(uuid UUID, origin VARCHAR, destination VARCHAR, departureTime VARCHAR, arrivalTime VARCHAR, numSeats INT, numSeatsAvailable INT, price DOUBLE, driver UUID, passenger UUID)";
  }
}
