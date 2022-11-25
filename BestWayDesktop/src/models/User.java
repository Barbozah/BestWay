package models;

import java.util.UUID;

public interface User {
  public UUID getUuid();

  public String getName();

  public String getEmail();

  public void setName(String name);

  public void setEmail(String email);

  public void setPassword(String password);

  public boolean checkPassword(String password);

  public String getAddress();

  public void setAddress(String address);

  public String getPhoneNumber();

  public void setPhoneNumber(String phoneNumber);

  public int getRating();

  public void setRating(int rating);

  public static String getSQLString() {
    return "user(uuid UUID, name VARCHAR, email VARCHAR, address VARCHAR, phone VARCHAR, rating INT, password VARCHAR)";
  }
}
