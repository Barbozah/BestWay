package models;

import java.util.UUID;

public class PassengerClient extends UserWithHashedPassword implements Passenger {

  private String paymentMethod;
  private double wallet;

  public PassengerClient(String name, String email, String password, String address, String phoneNumber,
      String paymentMethod, double wallet) {
    super(name, email, password, address, phoneNumber);
    this.paymentMethod = paymentMethod;
    this.wallet = wallet;
  }

  public PassengerClient(String uuid, String salt, String name, String email, String password,
      String address, String phoneNumber, float rating, String paymentMethod, double wallet) {
    super(name, email, password, address, phoneNumber);
    this.rating = rating;
    this.uuid = UUID.fromString(uuid);
    this.salt = salt;
    this.paymentMethod = paymentMethod;
    this.wallet = wallet;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public String getPaymentMethod() {
    return this.paymentMethod;
  }

  public void walletAdd(double amount) {
    this.wallet += amount;
  }

  public void walletSubtract(double amount) {
    this.wallet -= amount;
  }

  public double getWallet() {
    return this.wallet;
  }

  public void setWallet(double amount) {
    this.wallet = amount;
  }

  public String toString() {
    return String.format(
        "Passenger: %s, %s, %s, %s, %s, %s",
        this.getName(),
        this.getPhoneNumber());
  }
}