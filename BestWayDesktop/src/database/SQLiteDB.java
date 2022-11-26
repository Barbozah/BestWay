package database;

import java.sql.*;
import java.util.Map;

import models.User;
import models.Car;
import models.Driver;
import models.Passenger;
import models.Travel;

public class SQLiteDB implements Database {
  private String URL = "jdbc:sqlite:src/database/bestway.db";
  private static SQLiteDB instance;

  public static SQLiteDB getInstance() {
    if (SQLiteDB.instance == null) {
      SQLiteDB.instance = new SQLiteDB();
      return SQLiteDB.instance;
    }
    return new SQLiteDB();
  }

  private SQLiteDB() {
    System.out.println("Conectando ao SQLite database...");
    try (Connection connection = DriverManager.getConnection(URL)) {
      System.out.println("Conexão ao SQLite foi estabelecida.");

      Statement statement = connection.createStatement();

      statement.execute("CREATE TABLE IF NOT EXISTS " + User.getSQLString());

      statement.execute("CREATE TABLE IF NOT EXISTS " + Driver.getSQLString());

      statement.execute("CREATE TABLE IF NOT EXISTS " + Passenger.getSQLString());

      statement.execute("CREATE TABLE IF NOT EXISTS " + Car.getSQLString());

      statement.execute("CREATE TABLE IF NOT EXISTS " + Travel.getSQLString());

      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void insert(Map<String, ?> data, String table) {
    String keys = "(";
    String values = "(";
    for (String key : data.keySet()) {
      keys += key.split(" ")[0] + ", ";
      values += String.format("'" + key.split(" ")[1] + "', ", data.get(key));
    }
    keys = keys.substring(0, keys.length() - 2) + ")";
    values = values.substring(0, values.length() - 2) + ")";
    String sql = "INSERT INTO " + table + keys + " VALUES " + values;
    try (Connection connection = DriverManager.getConnection(URL)) {
      Statement statement = connection.createStatement();
      statement.execute(sql);
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void update(Map<String, ?> data, String table, String condition) {
    String set = "";
    for (Map.Entry<String, ?> entry : data.entrySet()) {
      String key = entry.getKey().split(" ")[0];
      String format = entry.getKey().split(" ")[1];
      set += key + String.format(" = '" + format + "', ", entry.getValue());
    }
    set = set.substring(0, set.length() - 2);
    String sql = "UPDATE " + table + " SET " + set + " WHERE " + condition;
    try (Connection connection = DriverManager.getConnection(URL)) {
      Statement statement = connection.createStatement();
      statement.execute(sql);
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void delete(String table, String condition) {
    String sql = "DELETE FROM " + table + " WHERE " + condition;
    try (Connection connection = DriverManager.getConnection(URL)) {
      Statement statement = connection.createStatement();
      statement.execute(sql);
      connection.close();
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public Object select(String query) {
    try (Connection connection = DriverManager.getConnection(URL)) {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(query);
      connection.close();
      return resultSet;
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
    return null;
  }
}
