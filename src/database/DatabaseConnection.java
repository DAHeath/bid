package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {
  private static final String dbClassName = "com.mysql.jdbc.Driver";
  private static final String ADDRESS = "jdbc:mysql://127.0.0.1/testdb";
  private static final String USERNAME = "testuser";
  private static final String PASSWORD = "test623";

  private Connection connection;
  private Properties properties;

  public static DatabaseConnection instance = new DatabaseConnection();

  private DatabaseConnection() {
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

  public void connect() {
    try {
      connection = DriverManager.getConnection(ADDRESS, properties);
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

  public void executeQuery(String query) {
    try {
      connection.prepareStatement(query).execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
