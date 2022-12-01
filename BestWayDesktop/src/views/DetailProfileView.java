package views;

public class DetailProfileView {

  public DetailProfileView(models.User user) {
    System.out.println("\nDetalhes de usuário");
    System.out.println("Nome: " + user.getName());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Telefone: " + user.getPhoneNumber());
    System.out.println("Endereço: " + user.getAddress());
    if (user instanceof models.Passenger) {
      System.out.println("Carteira: " + ((models.Passenger) user).getWallet());
      System.out.println("Método de pagamento: " + ((models.Passenger) user).getPaymentMethod());
    } else {
      System.out.println("Carteira: " + ((models.Driver) user).getWallet());
      System.out.println("Carro:");
      System.out.println("\tModelo: " + ((models.Driver) user).getCar().getModel());
      System.out.println("\tCor: " + ((models.Driver) user).getCar().getColor());
      System.out.println("\tPlaca: " + ((models.Driver) user).getCar().getLicensePlate());
    }

  }

}
