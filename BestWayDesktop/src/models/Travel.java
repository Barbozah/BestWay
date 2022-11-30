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

  public UUID getDriver();

  public UUID getPassenger();

  public void setOrigin(String origin);

  public void setDestination(String destination);

  public void setDepartureTime(String departureTime);

  public void setArrivalTime(String arrivalTime);

  public void setNumSeats(int numSeats);

  public static String getSQLString() {
    return "travel (uuid_travel UUID, uuid_driver UUID, uuid_passenger UUID, origin VARCHAR, destination VARCHAR, departure_time VARCHAR, arrival_time VARCHAR, seats INT, seats_available INT, price DOUBLE, PRIMARY KEY (uuid_travel), FOREIGN KEY (uuid_passenger) REFERENCES passenger(uuid), FOREIGN KEY (uuid_driver) REFERENCES driver(uuid))";
  }
}
