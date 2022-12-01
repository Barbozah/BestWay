package views;

import controllers.Controller;
import models.Passenger;
import models.User;

public class ProfileView {

  private User loggedUser;

  public ProfileView() {
    this.loggedUser = Controller.getInstance().getUserController().getLoggedUser();
    System.out.println("Olá " + this.loggedUser.getName());

    ViewServiceOptions options = new ViewServiceOptions();

    options.addOption("1", "Visualizar informações de usuário", new Callback() {
      public boolean callback() {
        new DetailProfileView(loggedUser);
        return false;
      }
    });

    options.addOption("2", "Editar perfil", new Callback() {
      public boolean callback() {
        new EditProfileView(loggedUser);
        return false;
      }
    });

    if (loggedUser instanceof Passenger) {
      options.addOption("3", "Solicitar uma viagem", new Callback() {
        public boolean callback() {
          new TravelView(loggedUser);
          return false;
        }
      });
    } else {
      options.addOption("3", "Aceitar viagens", new Callback() {
        public boolean callback() {
          new ListWaitingTravelsView(loggedUser);
          return false;
        }
      });
    }

    options.addOption("4", "Visualizar histórico de viagens", new Callback() {
      public boolean callback() {
        new TravelHistoryView(loggedUser);
        return false;
      }
    });

    options.addOption("5", "Adicionar créditos a carteira", new Callback() {
      public boolean callback() {
        new AddCreditView((Passenger) loggedUser);
        return false;
      }
    });

    options.addOption("<", "Voltar", new Callback() {
      public boolean callback() {
        return true;
      }
    });

    ViewService.getInstance().waitInput(options, "O que você deseja fazer?");
  }

}
