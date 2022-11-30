package views;

public class MainView {

  public MainView() {

    ViewServiceOptions options = new ViewServiceOptions();

    options.addOption("1", "Cadastrar Usuário", new Callback() {
      public boolean callback() {
        new RegisterUserView();
        return false;
      }
    });

    options.addOption("2", "Login", new Callback() {
      public boolean callback() {
        new LoginView();
        return false;
      }
    });

    options.addOption("x", "Sair", new Callback() {
      public boolean callback() {
        return true;
      }
    });

    System.out.println("Parabéns por escolher o melhor caminho!");

    ViewService.getInstance().waitInput(options, "O que você deseja fazer?");

  }
}
