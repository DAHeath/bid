package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Table {
  private Database database;
  private String name;
  private String primaryKey;
  private List<Attribute> attributes;

  public Table(Database database, String name) {
    this.database = database;
    this.name = name;
    attributes = new ArrayList<Attribute>();
  }

  public void addAttribute(Type type, String label) {
    attributes.add(new Attribute(type, label));
  }

  public void setPrimaryKey(String primaryKey) {
    this.primaryKey = primaryKey;
  }

  public void create() {
    String query = "CREATE TABLE " + name + "(";
    for (Attribute attribute: attributes)
      query += attribute.sql() + ", ";
    query = (primaryKey == null) ? query.substring(0, query.length()-2) : query + "PRIMARY KEY(" + primaryKey + ")";
    query += ");";
    database.updateQuery(query);
  }

  public void drop() {
    database.updateQuery("DROP TABLE " + name + ";");
  }

  public void insertRow(Row row) {
    String query = "INSERT INTO " + name + row.getInsertSql() + ";";
    database.updateQuery(query);
  }

  public void updateRow(Row row, String column, int value) {
    String query = "UPDATE " + name + " SET " + row.getUpdateSql() + " WHERE " +
            column + "=" + value + ";";
    database.updateQuery(query);
  }

  public int getMax(String column) {
    return database.getMax(name, column);
  }

  public List<Row> selectWhere(String column, int value) {
    String query = "SELECT * FROM " + name + " WHERE " + column + "=" + value + ";";
    ResultSet rs = database.selectQuery(query);
    List<Row> rows = new ArrayList<Row>();
    Row row;
    try {
      while (rs.next()) {
        row = new Row();
        for (Attribute attribute: attributes)
          row.addValue(attribute.label, rs.getString(attribute.label));
        rows.add(row);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return rows;
  }

  enum Type {
    INTEGER,
    //CHARACTER,
    FLOAT
  }

  private class Attribute {
    private final Type type;
    private final String label;

    Attribute(Type type, String label) {
      this.type = type;
      this.label = label;
    }

    public String sql() {
      return label + " " + type.toString();
    }
  }
}
