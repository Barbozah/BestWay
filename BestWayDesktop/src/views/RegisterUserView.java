package views;

import java.util.List;

import controllers.Controller;
import models.Car;

public class RegisterUserView {

  public RegisterUserView() {
    System.out.println("Tela de cadastro de usuário");
    ViewServiceOptions options = new ViewServiceOptions();

    options.addOption("1", "Cadastrar Passageiro", new Callback() {
      public boolean callback() {
        System.out.println("Digite o nome do passageiro: ");
        String name = ViewService.getInstance().readInput();
        System.out.println("Digite o email do passageiro: ");
        String email = ViewService.getInstance().readInput();
        System.out.println("Digite a senha do passageiro: ");
        String password = ViewService.getInstance().readPassword();
        System.out.println("Digite o número de telefone do passageiro: ");
        String phone = ViewService.getInstance().readInput();
        System.out.println("Digite o endereço do passageiro como latitude e longitude: ");
        String address = ViewService.getInstance().readInput();
        System.out.println("Digite o método de pagamento do passageiro: ");
        String paymentMethod = ViewService.getInstance().readInput();
        System.out.println("Digite o saldo na carteira do passageiro: ");
        String walletBalance = ViewService.getInstance().readInput();
        Controller.getInstance().getUserController().registerPassenger(
            name, email, password, address, phone, paymentMethod,
            Double.parseDouble(walletBalance));
        System.out.println("Passageiro cadastrado com sucesso!");
        return false;
      }
    });

    options.addOption("2", "Cadastrar Motorista", new Callback() {
      public boolean callback() {
        System.out.println("Digite o nome do motorista: ");
        String name = ViewService.getInstance().readInput();
        System.out.println("Digite o email do motorista: ");
        String email = ViewService.getInstance().readInput();
        System.out.println("Digite a senha do motorista: ");
        String password = ViewService.getInstance().readPassword();
        System.out.println("Digite o número de telefone do motorista: ");
        String phone = ViewService.getInstance().readInput();
        System.out.println("Digite o endereço do motorista como latitude e longitude: ");
        String address = ViewService.getInstance().readInput();
        ViewServiceOptions optionsCar = new ViewServiceOptions();
        List<Car> cars = Controller.getInstance().getCarController().getCars();
        cars.forEach(car -> {
          optionsCar.addOption(
              String.format("%d", cars.indexOf(car) + 1),
              car.getMake() + " " + car.getModel() + " " + car.getYear(),
              new Callback() {
                public boolean callback() {
                  Controller.getInstance().getUserController().registerDriver(
                      name, email, password, address,
                      phone, car.getUuid().toString());
                  System.out.println("Motorista cadastrado com sucesso!");
                  return true;
                }
              });
        });
        optionsCar.addOption("0", "Adicionar um novo modelo", new Callback() {
          @Override
          public boolean callback() {
            Car car = RegisterCar.registerCar();
            Controller.getInstance().getUserController().registerDriver(
                name, email, password, address,
                phone, car.getUuid().toString());
            System.out.println("Motorista cadastrado com sucesso!");
            return true;
          }
        });
        ViewService.getInstance().waitInput(optionsCar, "Selecione o veículo do motorista: ");
        return false;
      }
    });

    options.addOption("<", "Voltar", new Callback() {
      @Override
      public boolean callback() {
        return true;
      }
    });

    ViewService.getInstance().waitInput(options, "Selecione o tipo de usuário a ser cadastrado: ");
  }

}
