package views;

import java.util.List;
import java.util.Map;

import controllers.Controller;
import models.TravelWaitingForDriver;
import models.User;

public class TravelView {

  public TravelView(User user) {
    System.out.println("Tela de cadastro de viagem");
    ViewServiceOptions options = new ViewServiceOptions();

    List<Map<String, String>> knowsAddresses = Controller.getInstance().getTravelController().getKnowsAddresses();

    int i = 1;
    for (Map<String, String> address : knowsAddresses) {
      options.addOption(
          String.format("%d", i),
          address.get("label") + " - " + address.get("address"),
          new Callback() {
            public boolean callback() {
              return true;
            }
          });
      i++;
    }
    options.addOption("<", "Voltar", new Callback() {
      public boolean callback() {
        return true;
      }
    });

    options.setDefaultOption(new Callback() {
      @Override
      public boolean callback() {
        return true;
      }
    });

    String origin = (String) ViewService.getInstance().waitInput(options,
        "Digite a origem (Latitude, Longitude) ou escolha um dos endereços");
    if (origin == null) {
      System.out.println("Origem inválida");
      return;
    }
    if (!origin.contains(",")) {
      try {
        int index = Integer.parseInt(origin) - 1;
        origin = knowsAddresses.get(index).get("lat") + "," + knowsAddresses.get(index).get("lng");
      } catch (Exception e) {
        System.out.println("Origem inválida");
        return;
      }
    }
    origin = origin.replace(" ", "");
    System.out.println("Digite o destino:");
    String destination = (String) ViewService.getInstance().waitInput(options,
        "Digite o destino (Latitude, Longitude) ou escolha um dos endereços");
    if (destination == null) {
      System.out.println("Destino inválido");
      return;
    }
    if (!destination.contains(",")) {
      try {
        int index = Integer.parseInt(destination) - 1;
        destination = knowsAddresses.get(index).get("lat") + "," + knowsAddresses.get(index).get("lng");
      } catch (Exception e) {
        System.out.println("Destino inválido");
        return;
      }
    }
    destination = destination.replace(" ", "");
    System.out.println("Digite a quantidade de passageiros:");
    int vacancies = -1;
    while (vacancies == -1) {
      try {
        vacancies = Integer.parseInt(ViewService.getInstance().readInput());
        break;
      } catch (Exception e) {
        System.out.println("Valor inválido");
        continue;
      }
    }
    try {
      TravelWaitingForDriver travelWaiting = controllers.Controller.getInstance().getTravelController().waitForDriver(
          user.getUuid().toString(), origin, destination, vacancies);
      System.out.println(travelWaiting);
      System.out.println("Aguardando motorista...");
    } catch (Exception e) {
      System.out.println("Erro ao cadastrar viagem, verifique os dados e tente novamente");
    }
  }

}
