package views;

import java.util.List;

import controllers.Controller;
import models.Travel;
import models.TravelWaitingForDriver;
import models.User;

public class TravelHistoryView {

  public TravelHistoryView(User user) {
    System.out.println("Tela de histórico de viagens");
    String userUuid = user.getUuid().toString();
    List<Travel> travels = Controller.getInstance().getTravelController().getTravelsByUser(userUuid);

    ViewServiceOptions options = new ViewServiceOptions();

    int i = 1;
    for (Travel travel : travels) {
      options.addOption(
          String.format("%d", i),
          travel.getDestination() + ", " + travel.getDepartureTime() + ", R$ " + travel.getPrice(),
          new Callback() {
            public boolean callback() {
              new DetailTravelView(travel);
              return false;
            }
          });
      i++;
    }

    List<TravelWaitingForDriver> travelsWaitingForDriver = Controller.getInstance().getTravelController()
        .getTravelsWaitingForDriver();

    for (Travel travel : travelsWaitingForDriver) {
      options.addOption(
          String.format("%d", i),
          "[Aguardando] " + travel.getDestination() + ", R$ " + travel.getPrice(),
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
