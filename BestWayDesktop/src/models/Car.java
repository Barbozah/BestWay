package models;

import java.util.UUID;

public interface Car {
  public UUID getUuid();

  public String getMake();

  public String getModel();

  public String getYear();

  public String getLicensePlate();

  public String getColor();

  public int getNumSeats();

  public void setMake(String make);

  public void setModel(String model);

  public void setYear(String year);

  public void setLicensePlate(String licensePlate);

  public void setColor(String color);

  public void setNumSeats(int numSeats);

  public static String getSQLString() {
    return """
        car (
          uuid_car UUID,
          make VARCHAR,
          model VARCHAR,
          color VARCHAR,
          year INT,
          seats INT,
          license_plate VARCHAR,
          PRIMARY KEY (uuid_car)
        )
        """;
  }
}
