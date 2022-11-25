package database;

import java.sql.*;

import models.User;
import models.Car;
import models.Driver;
import models.Passenger;
import models.Travel;

public class SQLiteDB implements Database {
  private Connection connection;
  private static SQLiteDB instance;

  public static SQLiteDB getInstance() {
    if (instance == null) {
      instance = new SQLiteDB();
    }
    return new SQLiteDB();
  }

  private SQLiteDB() {
    System.out.println("Conectando ao SQLite database...");
    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database/database.db")) {
      System.out.println("Conexão ao SQLite foi estabelecida.");

      Statement statement = connection.createStatement();

      statement.execute("CREATE TABLE IF NOT EXISTS " + User.getSQLString());

      statement.execute("CREATE TABLE IF NOT EXISTS " + Driver.getSQLString());

      statement.execute("CREATE TABLE IF NOT EXISTS " + Passenger.getSQLString());

      statement.execute("CREATE TABLE IF NOT EXISTS " + Car.getSQLString());

      statement.execute("CREATE TABLE IF NOT EXISTS " + Travel.getSQLString());

    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void connect() {
    try (Connection connection = DriverManager.getConnection("jdbc:sqlite:src/database/database.db")) {
      if (this.connection != null) {
        this.connection.close();
      }
      this.connection = connection;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  public void disconnect() {
    System.out.println("Disconnecting from SQLite database...");
    try {
      if (connection != null) {
        connection.close();
      }
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
    }
  }

  @Override
  public void insert(String data, String table) {
    String sql = "INSERT INTO " + table + " VALUES(" + data + ")";
    if (this.connection != null)
      this.connect();
    try (Statement statement = this.connection.createStatement()) {
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void update(String data, String table, String condition) {
    String sql = "UPDATE " + table + " SET " + data + " WHERE " + condition;
    if (this.connection != null)
      this.connect();
    try (Statement statement = this.connection.createStatement()) {
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void delete(String table, String condition) {
    String sql = "DELETE FROM " + table + " WHERE " + condition;
    if (this.connection != null)
      this.connect();
    try (Statement statement = this.connection.createStatement()) {
      statement.execute(sql);
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public Object select(String table, String query) {
    if (this.connection != null)
      this.connect();
    try (Statement statement = this.connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery(query);
      return resultSet;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}
