package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import database.TravelRepository;
import database.UserRepository;
import models.Driver;
import models.Passenger;
import models.Travel;
import models.TravelServiceInfo;
import models.TravelWaitingForDriver;

public class TravelController {
  private List<TravelWaitingForDriver> travels;
  private List<Map<String, String>> knowsAddresses;
  private final float pricePerKm = 0.5f;

  public TravelController() {
    travels = new ArrayList<TravelWaitingForDriver>();
    knowsAddresses = new ArrayList<Map<String, String>>() {
      {
        add(new HashMap<String, String>() {
          {
            put("label", "UFRPE");
            put("address", "Rua Dom Manoel de Medeiros, S/N - Dois Irmãos, Recife - PE, 52171-900");
            put("lat", "-8.014610");
            put("lng", "-34.950474");
          }
        });
        add(new HashMap<String, String>() {
          {
            put("label", "UFPE");
            put("address", "Av. Prof. Moraes Rego, 1235 - Cidade Universitária, Recife - PE, 50670-901");
            put("lat", "-8.05070468");
            put("lng", "-34.9508813");
          }
        });
        add(new HashMap<String, String>() {
          {
            put("label", "Shopping Recife");
            put("address", "R. Padre Carapuceiro, 777 - Boa Viagem, Recife - PE, 51020-900");
            put("lat", "-8.119027");
            put("lng", "-34.904828");
          }
        });
        add(new HashMap<String, String>() {
          {
            put("label", "Marco Zero");
            put("address", "Recife, PE, 50020-360");
            put("lat", "-8.06317069");
            put("lng", "-34.87115532");
          }
        });
        add(new HashMap<String, String>() {
          {
            put("label", "Aeroporto Internacional dos Guararapes");
            put("address", "Praça Min. Salgado Filho, s/n - Imbiribeira, Recife - PE, 51210-902");
            put("lat", "-8.13088517");
            put("lng", "-34.91893351");
          }
        });
      }
    };
  }

  public List<Map<String, String>> getKnowsAddresses() {
    return knowsAddresses;
  }

  public TravelWaitingForDriver waitForDriver(
      String passenger, String origin, String destination, int numSeats) throws Exception {
    TravelServiceInfo tinfo = RoutingService.getTravelInfo(origin, destination);
    double price = tinfo.distance / 1000 * pricePerKm;
    float estimatedDuration = tinfo.duration / 60;
    TravelWaitingForDriver travel = new TravelWaitingForDriver(
        passenger, origin, destination, estimatedDuration, numSeats, price);
    travels.add(travel);
    return travel;
  }

  public List<TravelWaitingForDriver> getTravelsWaitingForDriver() {
    return travels;
  }

  public void acceptTravel(TravelWaitingForDriver travel) {
    travels.remove(travel);
    Driver driver = (Driver) Controller.getInstance().getUserController().getLoggedUser();
    createTravel(
        driver.getUuid().toString(), driver.getCar().getUuid().toString(),
        travel.getPassenger(), travel.getOrigin(), travel.getDestination(),
        travel.getEstimatedDuration(), travel.getPrice());
  }

  public void createTravel(
      String uuidDriver, String uuidCar, String uuidPassenger,
      String origin, String destination, float estimatedDuration, double price) {
    Passenger p = UserRepository.getInstance().getPassenger(uuidPassenger);
    Driver d = UserRepository.getInstance().getDriver(uuidDriver);
    long now = TimeUtils.getCurrentTime();
    String departureTime = TimeUtils.getFormattedDate(now);
    String arrivalTime = TimeUtils.getFormattedDate(TimeUtils.addTime(now, estimatedDuration));
    TravelRepository.getInstance().insertTravel(
        d, p, origin, destination, departureTime, arrivalTime, price);
  }

  public void rateDriver(String uuidTravel, float rating) {
    Travel t = getTravel(uuidTravel);
    Controller.getInstance().getUserController().ratingDriver(t.getDriver(), rating);
  }

  public void deleteTravel(String uuid) {
    TravelRepository.getInstance().deleteTravel(uuid);
  }

  public Travel getTravel(String uuid) {
    return TravelRepository.getInstance().getTravel(uuid);
  }

  public List<Travel> getTravels() {
    return TravelRepository.getInstance().getAllTravels();
  }

  public List<Travel> getTravelsByUser(String uuid) {
    return TravelRepository.getInstance().getTravelsByUser(uuid);
  }
}
