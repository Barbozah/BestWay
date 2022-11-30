package models;

public interface Passenger extends User {

  public void setPaymentMethod(String paymentMethod);

  public String getPaymentMethod();

  public void walletAdd(double amount);

  public void walletSubtract(double amount);

  public double getWallet();

  public void setWallet(double amount);

  public static String getSQLString() {
    return """
          passenger (
            uuid UUID,
            payment_method VARCHAR,
            wallet FLOAT,
            PRIMARY KEY (uuid),
            FOREIGN KEY (uuid) REFERENCES user(uuid)
          )
        """;
  }
}