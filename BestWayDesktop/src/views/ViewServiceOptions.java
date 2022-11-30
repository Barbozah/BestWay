package views;

import java.util.HashMap;
import java.util.Map;

final class Option {
  String label;
  Callback callback;
}

public class ViewServiceOptions {
  private Map<String, Option> options;

  public ViewServiceOptions() {
    this.options = new HashMap<String, Option>();
  }

  public void addOption(String key, String label, Callback callback) {
    Option option = new Option();
    option.label = label;
    option.callback = callback;
    this.options.put(key.toLowerCase(), option);
  }

  public void addOption(String key, Callback callback) {
    this.addOption(key, null, callback);
  }

  public Callback getOption(String key) {
    return this.options.get(key).callback;
  }

  public String getLabel(String key) {
    return this.options.get(key).label;
  }

  public Map<String, Option> getOptions() {
    return this.options;
  }
}
