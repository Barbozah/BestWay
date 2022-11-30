package controllers;

public class Controller {
  UserController userController;
  private static Controller instance;

  private Controller() {
    this.userController = new UserController();
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
}
