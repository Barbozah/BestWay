package views;

import controllers.Controller;
import models.Car;
import models.Passenger;
import models.User;

public class EditProfileView {

  protected static String userLabel = null;

  public EditProfileView(User loggedUser) {
    System.out.println("Tela de edição de perfil");

    EditProfileView.userLabel = loggedUser instanceof Passenger ? "passageiro" : "motorista";

    if (loggedUser instanceof models.Passenger) {
      System.out.println(String.format(
          "Carteira: R$%.2f",
          ((models.Passenger) loggedUser).getWallet()));
      System.out.println("Método de pagamento: " + ((models.Passenger) loggedUser).getPaymentMethod());
    } else if (loggedUser instanceof models.Driver) {
      System.out.println("Carro: ");
      System.out.println("\tModelo do carro: " + ((models.Driver) loggedUser).getCar().getModel());
      System.out.println("\tCor do carro: " + ((models.Driver) loggedUser).getCar().getColor());
      System.out.println("\tPlaca do carro: " + ((models.Driver) loggedUser).getCar().getLicensePlate());
      System.out.println();
    }

    ViewServiceOptions options = new ViewServiceOptions();

    options.addOption("1", "Visualizar informações", new Callback() {
      public boolean callback() {
        new DetailProfileView(loggedUser);
        return false;
      }
    });

    options.addOption("2", "Editar nome", new Callback() {
      public boolean callback() {
        System.out.println("Digite o nome do " + EditProfileView.userLabel + ": ");
        String name = ViewService.getInstance().readInput();
        loggedUser.setName(name);
        return false;
      }
    });

    options.addOption("3", "Editar email", new Callback() {
      public boolean callback() {
        System.out.println("Digite o email do " + EditProfileView.userLabel + ": ");
        String email = ViewService.getInstance().readInput();
        loggedUser.setEmail(email);
        return false;
      }
    });

    options.addOption("4", "Editar senha", new Callback() {
      public boolean callback() {
        System.out.println("Digite a senha do " + EditProfileView.userLabel + ": ");
        String password = ViewService.getInstance().readInput();
        loggedUser.setPassword(password);
        return false;
      }
    });

    options.addOption("5", "Editar telefone", new Callback() {
      public boolean callback() {
        System.out.println("Digite o telefone do " + EditProfileView.userLabel + ": ");
        String phoneNumber = ViewService.getInstance().readInput();
        loggedUser.setPhoneNumber(phoneNumber);
        return false;
      }
    });

    options.addOption("6", "Editar endereço", new Callback() {
      public boolean callback() {
        System.out.println("Digite o endereço do " + EditProfileView.userLabel + ": ");
        String address = ViewService.getInstance().readInput();
        loggedUser.setAddress(address);
        return false;
      }
    });

    if (loggedUser instanceof models.Driver) {
      options.addOption("7", "Substituir Carro", new Callback() {
        public boolean callback() {
          System.out.println("Tela de substituição de carro");
          Car car = RegisterCar.registerCar();
          ((models.Driver) loggedUser).setCar(car);
          Controller.getInstance().getCarController().updateCar(car);
          return false;
        }
      });
      options.addOption("8", "Editar Carro", new Callback() {
        public boolean callback() {
          System.out.println("Tela de edição de carro");
          Car car = RegisterCar.updateCar(((models.Driver) loggedUser).getCar());
          ((models.Driver) loggedUser).setCar(car);
          return false;
        }
      });
    } else {
      options.addOption("7", "Editar método de pagamento", new Callback() {
        public boolean callback() {
          System.out.println("Digite o método de pagamento do " + EditProfileView.userLabel + ": ");
          String paymentMethod = ViewService.getInstance().readInput();
          ((models.Passenger) loggedUser).setPaymentMethod(paymentMethod);
          return false;
        }
      });
      options.addOption("8", "Editar carteira", new Callback() {
        public boolean callback() {
          System.out.println("Digite o valor da carteira do " + EditProfileView.userLabel + ": ");
          String wallet = ViewService.getInstance().readInput();
          ((models.Passenger) loggedUser).setWallet(Double.parseDouble(wallet));
          return false;
        }
      });
    }

    options.addOption("<", "Salvar alterações e voltar", new Callback() {
      public boolean callback() {
        Controller.getInstance().getUserController().updateUser(loggedUser);
        return true;
      }
    });

    options.addOption("x", "Voltar sem salvar as alterações", new Callback() {
      public boolean callback() {
        return true;
      }
    });

    ViewService.getInstance().waitInput(options, "O que deseja editar?");
  }

}
