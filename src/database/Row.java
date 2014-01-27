package database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Row {
  private Map<String, String> values;
  private String labelSql;
  private String valueSql;

  public Row() {
    values = new HashMap<String, String>();
  }

  public void addValue(String label, String value) {
    values.put(label, value);
  }

  public void addValue(String label, int value) {
    addValue(label, "" + value);
  }

  public void addValue(String label, float value) {
    addValue(label, "" +value);
  }

  public int intAt(String label) {
    return Integer.parseInt(values.get(label));
  }

  public float floatAt(String label) {
    return Float.parseFloat(values.get(label));
  }

  /*
  public String stringAt(String label) {
    return values.get(label);
  }
  */

  public String getInsertSql() {
    Collection<String> keys = values.keySet();
    initializeStrings();
    for (String key: keys)
      addPairToStrings(key);
    removeTrailingCommas();
    return combineStrings();
  }

  public String getUpdateSql() {
    String res = "";
    for (String key: values.keySet())
      res += key + "=" + values.get(key) +",";
    return res.substring(0, res.length()-1);
  }

  private void initializeStrings() {
    labelSql = "(";
    valueSql = "(";
  }

  private void addPairToStrings(String key) {
    labelSql += key + ",";
    valueSql += values.get(key) + ",";
  }

  private void removeTrailingCommas() {
    labelSql = labelSql.substring(0, labelSql.length() - 1);
    valueSql = valueSql.substring(0, valueSql.length() - 1);
  }

  private String combineStrings() {
    return labelSql + ") VALUES " + valueSql + ")";
  }

  @Override
  public String toString() {
    String rep = "<<";
    for (String key: values.keySet())
      rep += key + ":" + values.get(key) + "  ";
    rep += ">>";
    return rep;
  }
}
