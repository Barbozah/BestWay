package views;

import java.util.HashMap;
import java.util.Map;

public class ViewServiceOptions {
  private Map<String, Callback> options;

  public ViewServiceOptions() {
    this.options = new HashMap<String, Callback>();
  }

  public void addOption(String option, Callback callback) {
    this.options.put(option, callback);
  }

  public Map<String, Callback> getOptions() {
    return this.options;
  }
}
