package models;

import java.util.UUID;

public abstract class UserWithHashedPassword implements User {
  protected UUID uuid;
  private String name;
  private String email;
  private String address;
  private String phoneNumber;
  protected float rating;
  protected String password;
  protected String salt;
  private PasswordEncrypt encryptor;

  public UserWithHashedPassword(
      String name, String email, String password,
      String address, String phoneNumber) {
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.email = email;
    this.encryptor = new PBEKeySpecEncryptor(10000, 256);
    this.salt = this.encryptor.getSaltValue(30);
    this.password = this.encryptor.generateSecurePassword(password, this.salt);
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.rating = 5;
  }

  public UserWithHashedPassword(
      String name, String email, String password, String address,
      String phoneNumber, PasswordEncrypt encryptor) {
    this.uuid = UUID.randomUUID();
    this.name = name;
    this.email = email;
    this.encryptor = encryptor;
    this.salt = encryptor.getSaltValue(30);
    this.password = encryptor.generateSecurePassword(password, this.salt);
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.rating = 5;
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
    return this.encryptor.verifyUserPassword(password, this.password, this.salt);
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.salt = this.encryptor.getSaltValue(30);
    this.password = this.encryptor.generateSecurePassword(password, this.salt);
  }

  public String getSalt() {
    return this.salt;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float rating) {
    this.rating = rating;
  }
}
