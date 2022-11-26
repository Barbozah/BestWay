package models;

public class PassengerClient extends UserWithHashedPassword implements Passenger {

  private String paymentMethod;
  private double wallet;

  public PassengerClient(String name, String email, String password, String address, String phoneNumber,
      String paymentMethod, double wallet) {
    super(name, email, password, address, phoneNumber);
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
}