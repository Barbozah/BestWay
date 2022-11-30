package models;

import java.util.UUID;

public class CarDefault implements Car {

  private UUID uuid;
  private String make;
  private String model;
  private String color;
  private String year;
  private int seats;
  private String licensePlate;

  public CarDefault(String make, String model, String color, String year, int seats, String licensePlate) {
    this.uuid = UUID.randomUUID();
    this.make = make;
    this.model = model;
    this.color = color;
    this.year = year;
    this.seats = seats;
    this.licensePlate = licensePlate;
  }

  public CarDefault(String uuid, String make, String model, String color, String year, int seats, String licensePlate) {
    this.uuid = UUID.fromString(uuid);
    this.make = make;
    this.model = model;
    this.color = color;
    this.year = year;
    this.seats = seats;
    this.licensePlate = licensePlate;
  }

  @Override
  public UUID getUuid() {
    return this.uuid;
  }

  @Override
  public String getMake() {
    return this.make;
  }

  @Override
  public String getModel() {
    return this.model;
  }

  @Override
  public String getYear() {
    return this.year;
  }

  @Override
  public String getLicensePlate() {
    return this.licensePlate;
  }

  @Override
  public String getColor() {
    return this.color;
  }

  @Override
  public int getNumSeats() {
    return this.seats;
  }

  @Override
  public void setMake(String make) {
    this.make = make;
  }

  @Override
  public void setModel(String model) {
    this.model = model;
  }

  @Override
  public void setYear(String year) {
    this.year = year;
  }

  @Override
  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  @Override
  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public void setNumSeats(int numSeats) {
    this.seats = numSeats;
  }

}
