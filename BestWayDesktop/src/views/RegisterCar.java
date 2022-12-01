package views;

import controllers.Controller;
import models.Car;

public class RegisterCar {

  public static Car registerCar() {
    System.out.println("Digite a fabricante do veículo: ");
    String make = ViewService.getInstance().readInput();
    System.out.println("Digite o modelo do veículo: ");
    String model = ViewService.getInstance().readInput();
    System.out.println("Digite o ano do veículo: ");
    String year = ViewService.getInstance().readInput();
    System.out.println("Digite a cor do veículo: ");
    String color = ViewService.getInstance().readInput();
    System.out.println("Digite a placa do veículo: ");
    String plate = ViewService.getInstance().readInput();
    System.out.println("Digite o número de assentos do veículo: ");
    String seats = ViewService.getInstance().readInput();
    Car car = Controller.getInstance().getCarController().registerCar(
        make, model, year, color, Integer.parseInt(seats), plate);
    System.out.println("Veículo cadastrado com sucesso!");
    return car;
  }

  public static Car updateCar(Car car) {
    ViewServiceOptions options = new ViewServiceOptions();
    options.addOption("1", "Fabricante" + car.getMake(), new Callback() {
      @Override
      public boolean callback() {
        System.out.println("Digite a fabricante do veículo: ");
        String make = ViewService.getInstance().readInput();
        car.setMake(make);
        return false;
      }
    });
    options.addOption("2", "Modelo" + car.getModel(), new Callback() {
      @Override
      public boolean callback() {
        System.out.println("Digite o modelo do veículo: ");
        String model = ViewService.getInstance().readInput();
        car.setModel(model);
        return false;
      }
    });
    options.addOption("3", "Ano" + car.getYear(), new Callback() {
      @Override
      public boolean callback() {
        System.out.println("Digite o ano do veículo: ");
        String year = ViewService.getInstance().readInput();
        car.setYear(year);
        return false;
      }
    });
    options.addOption("4", "Cor" + car.getColor(), new Callback() {
      @Override
      public boolean callback() {
        System.out.println("Digite a cor do veículo: ");
        String color = ViewService.getInstance().readInput();
        car.setColor(color);
        return false;
      }
    });
    options.addOption("5", "Placa" + car.getLicensePlate(), new Callback() {
      @Override
      public boolean callback() {
        System.out.println("Digite a placa do veículo: ");
        String plate = ViewService.getInstance().readInput();
        car.setLicensePlate(plate);
        return false;
      }
    });
    options.addOption("6", "Número de assentos do veículos", new Callback() {
      @Override
      public boolean callback() {
        System.out.println("Digite o número de assentos do veículo: ");
        String seats = ViewService.getInstance().readInput();
        car.setNumSeats(Integer.parseInt(seats));
        return false;
      }
    });
    options.addOption("<", "Salvar alterações e voltar", new Callback() {
      @Override
      public boolean callback() {
        Controller.getInstance().getCarController().updateCar(car);
        return true;
      }
    });
    options.addOption("x", "Voltar sem salvar alterações", new Callback() {
      @Override
      public boolean callback() {
        return true;
      }
    });
    ViewService.getInstance().waitInput(options, "O que você deseja alterar?");
    return car;
  }
}
