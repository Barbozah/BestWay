package views;

import controllers.Controller;
import models.Driver;
import models.Passenger;
import models.Travel;
import models.TravelWaitingForDriver;
import models.User;

public class DetailTravelView {

  public DetailTravelView(Travel travel) {
    System.out.println("Tela de detalhes da viagem");
    System.out.println("Origem: " + travel.getOrigin());
    System.out.println("Destino: " + travel.getDestination());
    try {
      System.out.println("Data de partida: " + travel.getDepartureTime());
      System.out.println("Data de chegada: " + travel.getArrivalTime());
    } catch (UnsupportedOperationException e) {
      System.out.println("Data de partida: Aguardando motorista");
      System.out.println("Data de chegada: Aguardando motorista");
    }
    System.out.println(String.format("Preço: R$%.2f", travel.getPrice()));
    try {
      System.out.println("Avaliação: " + travel.getRating());
    } catch (UnsupportedOperationException e) {
      System.out.println("Avaliação: Aguardando motorista");
    }
    User loggedUser = Controller.getInstance().getUserController().getLoggedUser();
    Driver driver = null;
    try {
      driver = (Driver) Controller.getInstance().getUserController().searchUser(travel.getDriver().toString());
      System.out.println("Motorista: " + driver.getName());
      System.out.println("Carro:");
      System.out.println("\tModelo: " + driver.getCar().getModel());
      System.out.println("\tCor: " + driver.getCar().getColor());
      System.out.println("\tPlaca: " + driver.getCar().getLicensePlate());
    } catch (UnsupportedOperationException e) {
      System.out.println("Motorista: Aguardando motorista");
    }
    Passenger passenger = (Passenger) Controller.getInstance().getUserController()
        .searchUser(travel.getPassenger().toString());
    System.out.println("Passageiro:");
    System.out.println("\tNome: " + passenger.getName());
    System.out.println("\tNúmero de telefone: " + passenger.getPhoneNumber());

    ViewServiceOptions options = new ViewServiceOptions();

    options.addOption("1", "Informações do passageiro", new Callback() {
      public boolean callback() {
        new DetailProfileView(passenger);
        return false;
      }
    });

    if (driver != null) {
      options.addOption("2", "Informações do motorista", new Callback() {
        public boolean callback() {
          Driver driver = (Driver) Controller.getInstance().getUserController()
              .searchUser(travel.getDriver().toString());
          new DetailProfileView(driver);
          return false;
        }
      });

      if (loggedUser.getUuid().toString().equals(travel.getPassenger())) {
        options.addOption("3", "Avaliar motorista", new Callback() {
          public boolean callback() {
            System.out.println("Digite a nota que você daria para o motorista:");
            float rating = Float.NaN;
            while (Float.isNaN(rating)) {
              try {
                rating = Float.parseFloat(ViewService.getInstance().readInput());
              } catch (NumberFormatException e) {
                System.out.println("Nota inválida, digite novamente:");
              }
            }
            Controller.getInstance().getTravelController().rateDriver(travel.getUuid().toString(), rating);
            return false;
          }
        });
      }
    }

    if (travel instanceof TravelWaitingForDriver && loggedUser instanceof Driver) {
      options.addOption("2", "Aceitar viagem", new Callback() {
        public boolean callback() {
          Controller.getInstance().getTravelController().acceptTravel((TravelWaitingForDriver) travel);
          return true;
        }
      });
    }

    options.addOption("<", "Voltar", new Callback() {
      public boolean callback() {
        return true;
      }
    });

    ViewService.getInstance().waitInput(options, "Digite uma opção para visualizar: ");
  }

}
