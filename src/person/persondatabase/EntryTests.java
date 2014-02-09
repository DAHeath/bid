package person.persondatabase;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import person.PersonImpl;

import static database.Database.*;
import static junit.framework.Assert.*;

public class EntryTests {

  @Before
  public void setUp() {
    testDb.connect();
    PersonEntry.table.create();
  }

  @After
  public void tearDown() {
    PersonEntry.table.drop();
    testDb.disconnect();
  }

  @Test
  public void personPersistence() {
    new PersonEntry("TEST");
    assertEquals(new PersonImpl(0, "TEST"), PersonEntry.load(0));
  }
}
