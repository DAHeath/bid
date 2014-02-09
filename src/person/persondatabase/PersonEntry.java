package person.persondatabase;

import database.Row;
import database.Table;
import person.Person;
import person.PersonImpl;
import person.Request;

import static database.Database.*;

public class PersonEntry implements Person {
  public static Table table = new Table(testDb, "person");
  static {
    table.addAttribute(Table.Type.INTEGER, "id");
    table.addAttribute(Table.Type.CHARACTER, "name");
    table.setPrimaryKey("id");
  }

  public static Person load(int id) {
    Row row = table.selectWhere("id", id).get(0);
    return new PersonEntry(id, row.stringAt("name"));
  }

  private Person person;

  private PersonEntry(int id, String name) {
    person = new PersonImpl(id, name);
  }

  public PersonEntry(String name) {
    this(table.getMax("id") + 1, name);
    save(name);
  }

  private void save(String name) {
    Row row = new Row();
    row.addValue("id", person.getId());
    row.addValue("name", name);
    table.insertRow(row);
  }

  @Override
  public void addFriend(Person person) {
    this.person.addFriend(person);
  }

  @Override
  public void requestFriend(Person person) {
    this.person.requestFriend(person);
  }

  @Override
  public boolean hasFriend(Person person) {
    return this.person.hasFriend(person);
  }

  @Override
  public void addRequest(Request request) {
    person.addRequest(request);
  }

  @Override
  public void acceptRequest(String requestName) {
    person.acceptRequest(requestName);
  }

  @Override
  public boolean hasRequest(String requestName) {
    return person.hasRequest(requestName);
  }

  @Override
  public int getId() {
    return person.getId();
  }

  @Override
  public int hashCode() {
    return person.hashCode();
  }

  @Override
  public boolean equals(Object o) {
    return o instanceof Person && person.equals(o);
  }

  @Override
  public String toString() {
    return person.toString();
  }
}
