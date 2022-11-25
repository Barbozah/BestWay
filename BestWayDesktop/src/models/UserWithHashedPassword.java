package models;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;

public abstract class UserWithHashedPassword implements User {
  private UUID uuid;
  private String name;
  private String email;
  private String address;
  private byte[] hashedPassword;
  private byte[] passwordSalt;
  private SecureRandom random;
  private MessageDigest md;

  public UserWithHashedPassword(String name, String email, String password) {
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.email = email;
    this.passwordSalt = new byte[16];
    random = new SecureRandom();
    random.nextBytes(this.passwordSalt);
    this.setPassword(password);
  }

  public UUID getUuid() {
    return this.uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public boolean checkPassword(String password) {
    this.md.update(this.passwordSalt);
    return MessageDigest.isEqual(password.getBytes(StandardCharsets.UTF_8), this.hashedPassword);
  }

  public void setPassword(String password) {
    try {
      this.md = MessageDigest.getInstance("SHA-512");
      this.md.update(this.passwordSalt);
      this.hashedPassword = this.md.digest(password.getBytes(StandardCharsets.UTF_8));
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("SHA-512 algorithm not found");
    }
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }
}
