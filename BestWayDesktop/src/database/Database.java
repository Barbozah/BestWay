package database;

public interface Database {
  public void connect();

  public void disconnect();

  public void insert(String data, String table);

  public void update(String data, String table, String condition);

  public void delete(String table, String condition);

  public Object select(String table, String query);
}
