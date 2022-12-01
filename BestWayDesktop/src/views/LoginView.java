package views;

import controllers.Controller;
import models.User;

public class LoginView {
  private String email;
  private String password;

  public LoginView() {
    System.out.println("Tela de login");
    System.out.print("Digite seu email: ");
    this.email = ViewService.getInstance().readInput();
    System.out.print("Digite sua senha: ");
    this.password = ViewService.getInstance().readPassword();
    User user = Controller.getInstance().getUserController().loginUser(this.email, this.password);
    if (user == null) {
      System.out.println("Email ou senha inválidos");
    } else {
      System.out.println("Login realizado com sucesso!");
      ViewServiceOptions options = new ViewServiceOptions();

      options.addOption("1", "Meu perfil", new Callback() {
        public boolean callback() {
          new ProfileView();
          return false;
        }
      });

      options.addOption("2", "Visualizar usuários", new Callback() {
        public boolean callback() {
          new ListUsersView();
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

}
