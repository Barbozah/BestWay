package views;

public class AddCreditView {

  public AddCreditView(models.Passenger user) {
    System.out.println("Adicionar crédito");
    System.out.println("Crédito atual: " + user.getWallet());
    System.out.println("Digite o valor a ser adicionado:");
    double value = Double.NaN;
    while (Double.isNaN(value)) {
      try {
        value = Double.parseDouble(ViewService.getInstance().readInput());
        break;
      } catch (Exception e) {
        System.out.println("Valor inválido");
        continue;
      }
    }
    user.setWallet(user.getWallet() + value);
    controllers.Controller.getInstance().getUserController().updateUser(user);
  }

}
