package views;

import java.util.List;

import controllers.Controller;
import models.User;

public class ListUsersView {

  public ListUsersView() {
    System.out.println("Tela de listagem de usuários");

    List<User> users = Controller.getInstance().getUserController().listUsers();

    ViewServiceOptions options = new ViewServiceOptions();

    int i = 1;
    for (User user : users) {
      options.addOption(
          String.format("%d", i),
          user.getName() + " " + user.getPhoneNumber(),
          new Callback() {
            public boolean callback() {
              new DetailProfileView(user);
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

    ViewService.getInstance().waitInput(options, "Selecione um usuário para ver mais detalhes");
  }
}
