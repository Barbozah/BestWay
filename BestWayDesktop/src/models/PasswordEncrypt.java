package models;

public interface PasswordEncrypt {
  public String getSaltValue(int length);

  public byte[] hash(char[] password, byte[] salt);

  public String generateSecurePassword(String password, String salt);

  public boolean verifyUserPassword(String providedPassword, String securedPassword, String salt);
}
