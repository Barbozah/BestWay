package controllers;

public class Controller {
  UserController userController;
  CarController carController;
  TravelController travelController;

  private static Controller instance;

  private Controller() {
    this.userController = new UserController();
    this.carController = new CarController();
    this.travelController = new TravelController();
  }

  public static Controller getInstance() {
    if (instance == null) {
      instance = new Controller();
    }

    return instance;
  }

  public UserController getUserController() {
    return this.userController;
  }

  public CarController getCarController() {
    return this.carController;
  }

  public TravelController getTravelController() {
    return this.travelController;
  }
}
