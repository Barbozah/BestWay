package database;

import java.util.Map;

public interface Database {
  public void insert(Map<String, ?> data, String table);

  public void update(Map<String, ?> data, String table, String condition);

  public void delete(String table, String condition);

  public Object select(String query);
}
