package views;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ViewService {
  private static BufferedReader reader;
  private static ViewService instance;

  private ViewService() {
    reader = new BufferedReader(new InputStreamReader(System.in));
  }

  public static ViewService getInstance() {
    if (instance == null) {
      instance = new ViewService();
    }
    return instance;
  }

  public Object waitInput(ViewServiceOptions options, String message) {
    while (true) {
      System.out.println("\n" + message);
      for (String key : options.getOptions().keySet()) {
        if (options.getLabel(key) != null) {
          System.out.println(key + " - " + options.getLabel(key));
        } else {
          System.out.println(key);
        }
      }
      try {
        System.out.print("> ");
        String option = reader.readLine();
        if (options.getOptions().containsKey(option.toLowerCase())) {
          boolean back = options.getOption(option).callback();
          if (back)
            return option;
        } else {
          if (options.getDefaultOption() != null) {
            boolean back = options.getDefaultOption().callback();
            if (back)
              return option;
          }
        }
      } catch (Exception e) {
        System.out.println("Opção inválida");
      }
    }
  }

  public String readInput() {
    try {
      System.out.print("> ");
      return reader.readLine();
    } catch (Exception e) {
      return "";
    }
  }

  public String readPassword() {
    try {
      System.out.print("> ");
      return new String(System.console().readPassword());
    } catch (Exception e) {
      return "";
    }
  }

}
