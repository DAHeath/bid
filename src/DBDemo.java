import java.sql.*;
import java.util.Properties;

public class DBDemo
{
  // The JDBC DatabaseConnection Class.
  private static final String dbClassName = "com.mysql.jdbc.Driver";

  // Connection string. emotherearth is the database the program
  // is connecting to. You can include user and password after this
  // by adding (say) ?user=paulr&password=paulr. Not recommended!

  private static final String CONNECTION =
          "jdbc:mysql://127.0.0.1/testdb";

  public static void main(String[] args) throws
          ClassNotFoundException,SQLException
  {
    System.out.println(dbClassName);
    // Class.forName(xxx) loads the jdbc classes and
    // creates a drivermanager class factory
    Class.forName(dbClassName);

    // Properties for user and password. Here the user and password are both 'paulr'
    Properties p = new Properties();
    p.put("user","testuser");
    p.put("password","test623");

    // Now try to connect
    Connection c = DriverManager.getConnection(CONNECTION,p);

    PreparedStatement s = c.prepareStatement("CREATE TABLE blah (name char(20));");
    s.execute();
    c.close();
  }
}