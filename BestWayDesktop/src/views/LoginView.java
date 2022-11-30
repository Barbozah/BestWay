package views;

import controllers.Controller;

public class LoginView {
  private String email;
  private String password;

  public LoginView() {
    System.out.println("Tela de login");
    System.out.print("Digite seu email: ");
    this.email = ViewService.getInstance().readInput();
    System.out.print("Digite sua senha: ");
    this.password = ViewService.getInstance().readPassword();
    boolean authorized = Controller.getInstance().getUserController().loginUser(this.email, this.password);
    if (authorized) {
      System.out.println("Login realizado com sucesso!");
    } else {
      System.out.println("Email ou senha inválidos");
    }
  }

}
