package models;

import java.util.UUID;

public class TravelDefault implements Travel {

  private UUID uuid;
  private String origin;
  private String destination;
  private String departureTime;
  private String arrivalTime;
  private int numSeats;
  private int numSeatsAvailable;
  private double price;

  private UUID driver;
  private UUID passenger;

  public TravelDefault(
      Driver driver, Passenger passenger,
      String origin, String destination, Double price) {
    this.uuid = UUID.randomUUID();
    this.origin = origin;
    this.destination = destination;
    this.departureTime = "";
    this.arrivalTime = "";
    this.numSeats = 1;
    this.numSeatsAvailable = 1;
    this.price = price;
    this.driver = driver.getUuid();
    this.passenger = passenger.getUuid();
  }

  @Override
  public UUID getUuid() {
    return this.uuid;
  }

  @Override
  public String getOrigin() {
    return this.origin;
  }

  @Override
  public String getDestination() {
    return this.destination;
  }

  @Override
  public String getDepartureTime() {
    return this.departureTime;
  }

  @Override
  public String getArrivalTime() {
    return this.arrivalTime;
  }

  @Override
  public int getNumSeats() {
    return this.numSeats;
  }

  @Override
  public int getNumSeatsAvailable() {
    return this.numSeatsAvailable;
  }

  @Override
  public double getPrice() {
    return this.price;
  }

  @Override
  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public UUID getDriver() {
    return this.driver;
  }

  @Override
  public UUID getPassenger() {
    return this.passenger;
  }

  @Override
  public void setOrigin(String origin) {
    this.origin = origin;
  }

  @Override
  public void setDestination(String destination) {
    this.destination = destination;
  }

  @Override
  public void setDepartureTime(String departureTime) {
    this.departureTime = departureTime;
  }

  @Override
  public void setArrivalTime(String arrivalTime) {
    this.arrivalTime = arrivalTime;
  }

  @Override
  public void setNumSeats(int numSeats) {
    this.numSeats = numSeats;
  }

  @Override
  public void setNumSeatsAvailable(int numSeatsAvailable) {
    this.numSeatsAvailable = numSeatsAvailable;
  }
}
