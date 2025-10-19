package org.jkiss.dbeaver.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import java.util.HashMap;
import java.util.Map;
import org.jkiss.dbeaver.model.DBPEvent.Action;
import org.jkiss.dbeaver.model.struct.DBSDocumentConstraint;
import org.jkiss.dbeaver.model.struct.DBSDocumentContainer;
import org.jkiss.dbeaver.model.struct.DBSObject;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class DBPEventDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>Then return Enabled is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPEvent#DBPEvent(Action, DBSObject)}
   *   <li>{@link DBPEvent#setOptions(Map)}
   *   <li>{@link DBPEvent#getAction()}
   *   <li>{@link DBPEvent#getData()}
   *   <li>{@link DBPEvent#getEnabled()}
   *   <li>{@link DBPEvent#getObject()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPEvent.<init>(Action, DBSObject)",
    "void DBPEvent.<init>(Action, DBSObject, Object)",
    "void DBPEvent.<init>(Action, DBSObject, boolean)",
    "void DBPEvent.<init>(Action, DBSObject, boolean, Object)",
    "Action DBPEvent.getAction()",
    "Object DBPEvent.getData()",
    "Boolean DBPEvent.getEnabled()",
    "DBSObject DBPEvent.getObject()",
    "void DBPEvent.setOptions(Map)"
  })
  public void testGettersAndSetters_thenReturnEnabledIsNull() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    DBPEvent actualDbpEvent = new DBPEvent(Action.OBJECT_ADD, object);
    actualDbpEvent.setOptions(new HashMap<>());
    Action actualAction = actualDbpEvent.getAction();
    Object actualData = actualDbpEvent.getData();
    Boolean actualEnabled = actualDbpEvent.getEnabled();

    // Assert
    assertNull(actualEnabled);
    assertNull(actualData);
    assertEquals(Action.OBJECT_ADD, actualAction);
    assertSame(object, actualDbpEvent.getObject());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@link DBPEvent#RENAME}.
   *   <li>Then return Enabled is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPEvent#DBPEvent(Action, DBSObject, Object)}
   *   <li>{@link DBPEvent#setOptions(Map)}
   *   <li>{@link DBPEvent#getAction()}
   *   <li>{@link DBPEvent#getData()}
   *   <li>{@link DBPEvent#getEnabled()}
   *   <li>{@link DBPEvent#getObject()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPEvent.<init>(Action, DBSObject)",
    "void DBPEvent.<init>(Action, DBSObject, Object)",
    "void DBPEvent.<init>(Action, DBSObject, boolean)",
    "void DBPEvent.<init>(Action, DBSObject, boolean, Object)",
    "Action DBPEvent.getAction()",
    "Object DBPEvent.getData()",
    "Boolean DBPEvent.getEnabled()",
    "DBSObject DBPEvent.getObject()",
    "void DBPEvent.setOptions(Map)"
  })
  public void testGettersAndSetters_whenRename_thenReturnEnabledIsNull() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    DBPEvent actualDbpEvent = new DBPEvent(Action.OBJECT_ADD, object, DBPEvent.RENAME);
    actualDbpEvent.setOptions(new HashMap<>());
    Action actualAction = actualDbpEvent.getAction();
    Object actualData = actualDbpEvent.getData();
    Boolean actualEnabled = actualDbpEvent.getEnabled();

    // Assert
    assertNull(actualEnabled);
    assertEquals(Action.OBJECT_ADD, actualAction);
    assertSame(object, actualDbpEvent.getObject());
    assertSame(DBPEvent.RENAME, actualData);
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return Data is {@code null}.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPEvent#DBPEvent(Action, DBSObject, boolean)}
   *   <li>{@link DBPEvent#setOptions(Map)}
   *   <li>{@link DBPEvent#getAction()}
   *   <li>{@link DBPEvent#getData()}
   *   <li>{@link DBPEvent#getEnabled()}
   *   <li>{@link DBPEvent#getObject()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPEvent.<init>(Action, DBSObject)",
    "void DBPEvent.<init>(Action, DBSObject, Object)",
    "void DBPEvent.<init>(Action, DBSObject, boolean)",
    "void DBPEvent.<init>(Action, DBSObject, boolean, Object)",
    "Action DBPEvent.getAction()",
    "Object DBPEvent.getData()",
    "Boolean DBPEvent.getEnabled()",
    "DBSObject DBPEvent.getObject()",
    "void DBPEvent.setOptions(Map)"
  })
  public void testGettersAndSetters_whenTrue_thenReturnDataIsNull() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    DBPEvent actualDbpEvent = new DBPEvent(Action.OBJECT_ADD, object, true);
    actualDbpEvent.setOptions(new HashMap<>());
    Action actualAction = actualDbpEvent.getAction();
    Object actualData = actualDbpEvent.getData();
    Boolean actualEnabled = actualDbpEvent.getEnabled();

    // Assert
    assertNull(actualData);
    assertEquals(Action.OBJECT_ADD, actualAction);
    assertTrue(actualEnabled);
    assertSame(object, actualDbpEvent.getObject());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return Enabled.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link DBPEvent#DBPEvent(Action, DBSObject, boolean, Object)}
   *   <li>{@link DBPEvent#setOptions(Map)}
   *   <li>{@link DBPEvent#getAction()}
   *   <li>{@link DBPEvent#getData()}
   *   <li>{@link DBPEvent#getEnabled()}
   *   <li>{@link DBPEvent#getObject()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void DBPEvent.<init>(Action, DBSObject)",
    "void DBPEvent.<init>(Action, DBSObject, Object)",
    "void DBPEvent.<init>(Action, DBSObject, boolean)",
    "void DBPEvent.<init>(Action, DBSObject, boolean, Object)",
    "Action DBPEvent.getAction()",
    "Object DBPEvent.getData()",
    "Boolean DBPEvent.getEnabled()",
    "DBSObject DBPEvent.getObject()",
    "void DBPEvent.setOptions(Map)"
  })
  public void testGettersAndSetters_whenTrue_thenReturnEnabled() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act
    DBPEvent actualDbpEvent = new DBPEvent(Action.OBJECT_ADD, object, true, DBPEvent.RENAME);
    actualDbpEvent.setOptions(new HashMap<>());
    Action actualAction = actualDbpEvent.getAction();
    Object actualData = actualDbpEvent.getData();
    Boolean actualEnabled = actualDbpEvent.getEnabled();

    // Assert
    assertEquals(Action.OBJECT_ADD, actualAction);
    assertTrue(actualEnabled);
    assertSame(object, actualDbpEvent.getObject());
    assertSame(DBPEvent.RENAME, actualData);
  }

  /**
   * Test {@link DBPEvent#getOptions()}.
   *
   * <p>Method under test: {@link DBPEvent#getOptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBPEvent.getOptions()"})
  public void testGetOptions() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    // Act and Assert
    assertTrue(new DBPEvent(Action.OBJECT_ADD, object).getOptions().isEmpty());
  }

  /**
   * Test {@link DBPEvent#getOptions()}.
   *
   * <p>Method under test: {@link DBPEvent#getOptions()}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"Map DBPEvent.getOptions()"})
  public void testGetOptions2() {
    // Arrange
    DBSDocumentConstraint object = new DBSDocumentConstraint(mock(DBSDocumentContainer.class));

    DBPEvent dbpEvent = new DBPEvent(Action.OBJECT_ADD, object);
    dbpEvent.setOptions(new HashMap<>());

    // Act and Assert
    assertTrue(dbpEvent.getOptions().isEmpty());
  }
}
