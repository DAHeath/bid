package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
  private static final String dbClassName = "com.mysql.jdbc.Driver";
  private static final String TEST_ADDRESS = "jdbc:mysql://127.0.0.1/testdb";
  private static final String USERNAME = "testuser";
  private static final String PASSWORD = "test623";

  public static Database testDb = new Database(TEST_ADDRESS);

  private String address;
  private Connection connection;
  private Properties properties;

  private Database(String address) {
    this.address = address;
    initializeProperties();
    loadJdbc();
  }

  private void initializeProperties() {
    properties = new Properties();
    properties.put("user", USERNAME);
    properties.put("password", PASSWORD);
  }

  private void loadJdbc() {
    try {
      Class.forName(dbClassName);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * The database must be connected before queries can be made. It is the user's
   * responsibility to disconnect the database after use.
   */
  public void connect() {
    try {
      connection = DriverManager.getConnection(address, properties);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void disconnect() {
    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * For insert, delete, and update queries
   */
  public void updateQuery(String query) {
    try {
      connection.prepareStatement(query).executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public ResultSet selectQuery(String query) {
    try {
      return connection.prepareStatement(query).executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Select the max value of an integer column
   * @param table name of the table to search
   * @param colLabel label of the integer column
   * @return the largest value in the column
   */
  public int getMax(String table, String colLabel) {
    try {
      ResultSet rs = connection.prepareStatement("SELECT max(" + colLabel + ") FROM " + table +";").executeQuery();
      rs.next();
      return Integer.parseInt(rs.getString("max(" + colLabel + ")"));
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (NumberFormatException e) {
      return -1;
    }
    return 0;
  }
}