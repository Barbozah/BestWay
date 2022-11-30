package models;

import java.util.UUID;

public interface User {
  public UUID getUuid();

  public String getName();

  public String getEmail();

  public void setName(String name);

  public void setEmail(String email);

  public void setPassword(String password);

  public String getPassword();

  public boolean checkPassword(String password);

  public String getSalt();

  public String getAddress();

  public void setAddress(String address);

  public String getPhoneNumber();

  public void setPhoneNumber(String phoneNumber);

  public float getRating();

  public void setRating(float rating);

  public static String getSQLString() {
    return "user ( uuid UUID, name VARCHAR, email VARCHAR, address VARCHAR, phone VARCHAR, rating FLOAT, password VARCHAR, salt VARCHAR, PRIMARY KEY (uuid))";
  }
}
