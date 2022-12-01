package views;

import java.util.List;

import controllers.Controller;
import models.TravelWaitingForDriver;
import models.User;

public class ListWaitingTravelsView {

  public ListWaitingTravelsView(User user) {
    System.out.println("Tela de listagem de viagens aguardando motorista");

    List<TravelWaitingForDriver> travelsWaitingForDriver = Controller.getInstance().getTravelController()
        .getTravelsWaitingForDriver();

    ViewServiceOptions options = new ViewServiceOptions();

    int i = 1;
    for (TravelWaitingForDriver travel : travelsWaitingForDriver) {
      options.addOption(
          String.format("%d", i),
          travel.getDestination() + String.format(", R$%.2f", travel.getPrice()),
          new Callback() {
            public boolean callback() {
              new DetailTravelView(travel);
              return false;
            }
          });
      i++;
    }

    options.addOption("<", "Voltar", new Callback() {
      public boolean callback() {
        return true;
      }
    });

    ViewService.getInstance().waitInput(options, "Selecione uma viagem para ver mais detalhes");

  }

}
