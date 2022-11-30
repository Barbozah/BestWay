package views;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

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

  public String waitInput(ViewServiceOptions options, String message) {
    while (true) {
      System.out.println(message);
      for (Map.Entry<String, Callback> entry : options.getOptions().entrySet()) {
        System.out.println(entry.getKey());
      }
      try {
        String option = reader.readLine();
        if (options.getOptions().containsKey(option)) {
          options.getOptions().get(option).callback();
          return option;
        }
      } catch (Exception e) {
        System.out.println("Opção inválida");
      }
    }
  }

  public String readInput() {
    try {
      return reader.readLine();
    } catch (Exception e) {
      return "";
    }
  }

}
