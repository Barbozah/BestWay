package models;

import java.util.UUID;

public interface Passenger extends User {
  public UUID getUuid();

  public void setPaymentMethod(String paymentMethod);

  public String getPaymentMethod();

  public void walletAdd(double amount);

  public void walletSubtract(double amount);

  public double getWallet();

  public void setWallet(double amount);

  public static String getSQLString() {
    return "passenger(uuid UUID, paymentMethod VARCHAR, wallet DOUBLE)";
  }
}