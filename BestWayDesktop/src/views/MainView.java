package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MainView {

  public CadastrarUsuarioView cadastrarUsuarioView;
  public LoginView loginView;

  public MainView() {
    cadastrarUsuarioView = new CadastrarUsuarioView();
    loginView = new LoginView();

    ViewServiceOptions options = new ViewServiceOptions();

    options.addOption("1", new Callback() {
      public void callback() {
        cadastrarUsuarioView.cadastrarUsuario();
      }
    });

    options.addOption("2", new Callback() {
      public void callback() {
        loginView.login();
      }
    });

    options.addOption("3", new Callback() {
      public void callback() {
        System.exit(0);
      }
    });

    System.out.println("Parabéns por escolher o melhor caminho!");

    ViewService.getInstance().waitInput(options, "O que você deseja fazer?");

  }
}
