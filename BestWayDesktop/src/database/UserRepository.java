package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import models.Car;
import models.CarDefault;
import models.Driver;
import models.DriverClient;
import models.Passenger;
import models.PassengerClient;
import models.User;

public class UserRepository {

  private static UserRepository instance;
  private static Database db;

  private UserRepository() {
    db = SQLiteDB.getInstance();
  }

  public static UserRepository getInstance() {
    if (instance == null) {
      instance = new UserRepository();
    }
    return instance;
  }

  private void insertUser(User u) {
    db.insert(
        new HashMap<String, String>() {
          {
            put("uuid %s", u.getUuid().toString());
            put("name %s", u.getName());
            put("email %s", u.getEmail());
            put("password %s", u.getPassword());
            put("salt %s", u.getSalt());
            put("address %s", u.getAddress());
            put("phone %s", u.getPhoneNumber());
            put("rating %s", String.valueOf(u.getRating()));
          }
        },
        "user");
  }

  public Passenger insertPassenger(
      String name, String email, String password, String address,
      String phoneNumber, String paymentMethod, double wallet) {
    Passenger passenger = new PassengerClient(
        name, email, password, address,
        phoneNumber, paymentMethod, wallet);
    insertUser(passenger);
    db.insert(
        new HashMap<String, String>() {
          {
            put("uuid %s", passenger.getUuid().toString());
            put("payment_method %s", passenger.getPaymentMethod());
            put("wallet %s", String.valueOf(passenger.getWallet()));
          }
        },
        "passenger");
    return passenger;
  }

  public void insertPassenger(Passenger p) {
    insertUser(p);
    db.insert(
        new HashMap<String, String>() {
          {
            put("uuid %s", p.getUuid().toString());
            put("payment_method %s", p.getPaymentMethod());
            put("wallet %s", String.valueOf(p.getWallet()));
          }
        },
        "passenger");
  }

  public Driver insertDriver(String name, String email, String password, String address, String phoneNumber, Car car) {
    Driver driver = new DriverClient(name, email, password, address, phoneNumber, car);
    insertUser(driver);
    db.insert(
        new HashMap<String, String>() {
          {
            put("uuid %s", driver.getUuid().toString());
            put("uuid_car %s", driver.getCar().getUuid().toString());
          }
        },
        "driver");
    return driver;
  }

  public void insertDriver(Driver d) {
    insertUser(d);
    db.insert(
        new HashMap<String, String>() {
          {
            put("uuid %s", d.getUuid().toString());
            put("car_uuid %s", d.getCar().getUuid().toString());
          }
        },
        "driver");
  }

  public User getUser(String uuid) {
    List<User> result = this.searchUsers("user.uuid", uuid);
    if (result == null || result.size() == 0) {
      return null;
    }
    return result.get(0);
  }

  public Passenger getPassenger(String uuid) {
    List<User> result = this.searchUsers("user.uuid", uuid);
    if (result == null || result.size() == 0) {
      return null;
    }
    if (result.get(0) instanceof Passenger) {
      return (Passenger) result.get(0);
    }
    return null;
  }

  public Driver getDriver(String uuid) {
    List<User> result = this.searchUsers("user.uuid", uuid);
    if (result == null || result.size() == 0) {
      return null;
    }
    if (result.get(0) instanceof Driver) {
      return (Driver) result.get(0);
    }
    return null;
  }

  public void updateUser(User u) {
    db.update(
        new HashMap<String, String>() {
          {
            put("name %s", u.getName());
            put("email %s", u.getEmail());
            put("password %s", u.getPassword());
            put("address %s", u.getAddress());
            put("phone %s", u.getPhoneNumber());
            put("rating %s", String.valueOf(u.getRating()));
          }
        },
        "user",
        "uuid = " + u.getUuid().toString());
  }

  public void updatePassenger(Passenger p) {
    updateUser(p);
    db.update(new HashMap<>() {
      {
        put("payment_method %s", p.getPaymentMethod());
        put("wallet %s", String.valueOf(p.getWallet()));
      }
    }, "passenger", "uuid = " + p.getUuid().toString());
  }

  public void updateDriver(Driver d) {
    updateUser(d);
    db.update(new HashMap<>() {
      {
        put("uuid_car %s", d.getCar().getUuid().toString());
      }
    }, "driver", "uuid = " + d.getUuid().toString());
  }

  public void deleteUser(User u) {
    User storegedUser = getUser(u.getUuid().toString());
    if (storegedUser == null)
      return;
    if (storegedUser instanceof Passenger) {
      db.delete("passenger", "uuid = " + u.getUuid().toString());
    } else if (storegedUser instanceof Driver) {
      db.delete("driver", "uuid = " + u.getUuid().toString());
    }
    db.delete("user", "uuid = " + u.getUuid().toString());
  }

  public List<User> search(Map<String, String> terms) {
    String query = "SELECT * FROM user WHERE ";
    for (String term : terms.keySet()) {
      query += term + " = " + terms.get(term) + " AND ";
    }
    query = query.substring(0, query.length() - 5);
    return this.search(query);
  }

  public List<User> searchUsers(String column, String term) {
    return search("SELECT * FROM user " +
        "LEFT JOIN passenger ON user.uuid = passenger.uuid " +
        "LEFT JOIN driver ON user.uuid = driver.uuid " +
        "LEFT JOIN car ON driver.uuid_car = car.uuid_car " +
        "WHERE " + column + " LIKE '%" + term + "%'");
  }

  public List<User> search(String query) {
    List<Map<String, String>> result = db.select(query);

    Map<String, User> users = new HashMap<String, User>();

    if (result == null) {
      return null;
    }

    for (Map<String, String> data : result) {
      String uuid = data.get("user.uuid");
      User u = null;
      if (users.containsKey(uuid)) {
        u = users.get(uuid);
        if (u instanceof Passenger) {
          ((Passenger) u).setPaymentMethod(data.get("payment_method"));
          ((Passenger) u).setWallet(Double.parseDouble(data.get("wallet")));
        } else if (u instanceof Driver) {
          ((Driver) u).setCar(
              new CarDefault(
                  data.get("uuid_car"),
                  data.get("model"),
                  data.get("color"),
                  data.get("year"),
                  Integer.parseInt(data.get("seats")),
                  data.get("license_plate")));
        }
      } else {
        if (data.get("payment_method") != null) {
          u = new PassengerClient(
              data.get("uuid"),
              data.get("salt"),
              data.get("name"),
              data.get("email"),
              data.get("password"),
              data.get("address"),
              data.get("phone"),
              Float.parseFloat(data.get("rating")),
              data.get("payment_method"),
              Double.parseDouble(data.get("wallet")));
        } else if (data.get("uuid_car") != null) {
          u = new DriverClient(
              data.get("uuid"),
              data.get("salt"),
              data.get("name"),
              data.get("email"),
              data.get("password"),
              data.get("address"),
              data.get("phone"),
              Float.parseFloat(data.get("rating")),
              new CarDefault(
                  data.get("uuid_car"),
                  data.get("model"),
                  data.get("color"),
                  data.get("year"),
                  Integer.parseInt(data.get("seats")),
                  data.get("license_plate")));
        }
      }
      users.put(uuid, u);
    }

    return new ArrayList<User>(users.values());
  }

}