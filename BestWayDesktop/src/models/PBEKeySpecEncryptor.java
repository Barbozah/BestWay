package models;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBEKeySpecEncryptor implements PasswordEncrypt {

  private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private final Random random = new SecureRandom();
  private int iterations = 10000;
  private int keylength = 256;
  private String alphabet;

  public PBEKeySpecEncryptor(int iterations, int keylength) {
    this.iterations = iterations;
    this.keylength = keylength;
    this.alphabet = ALPHABET;
  }

  public PBEKeySpecEncryptor(int iterations, int keylength, String alphabet) {
    this.iterations = iterations;
    this.keylength = keylength;
    this.alphabet = alphabet;
  }

  @Override
  public String getSaltValue(int length) {
    StringBuilder finalval = new StringBuilder(length);
    for (int i = 0; i < length; i++) {
      finalval.append(alphabet.charAt(random.nextInt(alphabet.length())));
    }
    return new String(finalval);
  }

  @Override
  public byte[] hash(char[] password, byte[] salt) {
    PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keylength);
    Arrays.fill(password, Character.MIN_VALUE);
    try {
      SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
      return skf.generateSecret(spec).getEncoded();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new AssertionError("Erro enquanto aplicava hash na senha: " + e.getMessage(), e);
    } finally {
      spec.clearPassword();
    }
  }

  @Override
  public String generateSecurePassword(String password, String salt) {
    String finalval = null;
    byte[] securePassword = hash(password.toCharArray(), salt.getBytes());
    finalval = Base64.getEncoder().encodeToString(securePassword);
    return finalval;
  }

  @Override
  public boolean verifyUserPassword(String providedPassword, String securedPassword, String salt) {
    boolean finalval = false;
    // Gera uma nova senha segura com o mesmo salt
    String newSecurePassword = generateSecurePassword(providedPassword, salt);
    // Verifica se a senha gerada é igual a senha fornecida
    finalval = newSecurePassword.equalsIgnoreCase(securedPassword);
    return finalval;
  }

}
