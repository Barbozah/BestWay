package models;

import java.util.UUID;

import controllers.TimeUtils;

public class TravelWaitingForDriver implements Travel {

  private UUID uuid;
  private String origin;
  private String destination;
  private float estimatedDuration;
  private int numSeats;
  private double price;
  private String passenger;

  public TravelWaitingForDriver(String passenger, String origin, String destination,
      float estimatedDuration, int numSeats, double price) {
    this.uuid = UUID.randomUUID();
    this.origin = origin;
    this.destination = destination;
    this.estimatedDuration = estimatedDuration;
    this.numSeats = numSeats;
    this.price = price;
    this.passenger = passenger;
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
    throw new UnsupportedOperationException();
  }

  @Override
  public String getArrivalTime() {
    throw new UnsupportedOperationException();
  }

  @Override
  public int getNumSeats() {
    return this.numSeats;
  }

  @Override
  public int getNumSeatsAvailable() {
    throw new UnsupportedOperationException();
  }

  @Override
  public double getPrice() {
    return this.price;
  }

  @Override
  public String getDriver() {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getPassenger() {
    return this.passenger;
  }

  @Override
  public void setNumSeatsAvailable(int numSeatsAvailable) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setPrice(double price) {
    this.price = price;
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
    throw new UnsupportedOperationException();
  }

  @Override
  public void setArrivalTime(String arrivalTime) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setNumSeats(int numSeats) {
    this.numSeats = numSeats;
  }

  @Override
  public float getRating() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void setRating(float rating) {
    throw new UnsupportedOperationException();
  }

  public float getEstimatedDuration() {
    return this.estimatedDuration;
  }

  public TravelDefault setDriver(Driver driver) {
    String departureTime = TimeUtils.getFormattedDate(TimeUtils.getCurrentTime());
    String arrivalTime = TimeUtils
        .getFormattedDate(TimeUtils.addTime(TimeUtils.getCurrentTime(), (int) this.estimatedDuration));
    return new TravelDefault(
        this.uuid.toString(),
        driver.getUuid().toString(),
        this.passenger,
        this.origin,
        this.destination,
        departureTime,
        arrivalTime,
        this.numSeats,
        driver.getCar().getNumSeats(),
        this.price);
  }

  public String toString() {
    return "Informações da Viagem:\n" +
        "\tOrigem: " + this.origin + "\n" +
        "\tDestino: " + this.destination + "\n" +
        "\tDuração estimada: " + this.estimatedDuration + " min" + "\n" +
        "\tPreço estimado: R$" + String.format("%.2f", this.price);
  }

}
