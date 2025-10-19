package org.jkiss.dbeaver.model.impl.edit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import com.diffblue.cover.annotations.ContributionFromDiffblue;
import com.diffblue.cover.annotations.ManagedByDiffblue;
import com.diffblue.cover.annotations.MethodsUnderTest;
import org.jkiss.dbeaver.model.edit.DBEPersistAction;
import org.jkiss.dbeaver.model.edit.DBEPersistAction.ActionType;
import org.jkiss.dbeaver.model.exec.DBCException;
import org.jkiss.dbeaver.model.exec.DBCSession;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class SQLDatabasePersistActionDiffblueTest {
  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code INITIALIZER}.
   *   <li>Then return not Complex.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLDatabasePersistAction#SQLDatabasePersistAction(String, String, ActionType)}
   *   <li>{@link SQLDatabasePersistAction#afterExecute(DBCSession, Throwable)}
   *   <li>{@link SQLDatabasePersistAction#beforeExecute(DBCSession)}
   *   <li>{@link SQLDatabasePersistAction#getScript()}
   *   <li>{@link SQLDatabasePersistAction#getTitle()}
   *   <li>{@link SQLDatabasePersistAction#getType()}
   *   <li>{@link SQLDatabasePersistAction#isComplex()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLDatabasePersistAction.<init>(String, String, ActionType)",
    "void SQLDatabasePersistAction.<init>(String, String, ActionType, boolean)",
    "void SQLDatabasePersistAction.afterExecute(DBCSession, Throwable)",
    "void SQLDatabasePersistAction.beforeExecute(DBCSession)",
    "String SQLDatabasePersistAction.getScript()",
    "String SQLDatabasePersistAction.getTitle()",
    "ActionType SQLDatabasePersistAction.getType()",
    "boolean SQLDatabasePersistAction.isComplex()"
  })
  public void testGettersAndSetters_whenInitializer_thenReturnNotComplex() throws DBCException {
    // Arrange and Act
    SQLDatabasePersistAction actualSqlDatabasePersistAction =
        new SQLDatabasePersistAction("Dr", "Script", ActionType.INITIALIZER);
    DBCSession session = mock(DBCSession.class);
    actualSqlDatabasePersistAction.afterExecute(session, new Throwable());
    actualSqlDatabasePersistAction.beforeExecute(mock(DBCSession.class));
    String actualScript = actualSqlDatabasePersistAction.getScript();
    String actualTitle = actualSqlDatabasePersistAction.getTitle();
    ActionType actualType = actualSqlDatabasePersistAction.getType();

    // Assert
    assertEquals("Dr", actualTitle);
    assertEquals("Script", actualScript);
    assertEquals(ActionType.INITIALIZER, actualType);
    assertFalse(actualSqlDatabasePersistAction.isComplex());
  }

  /**
   * Test getters and setters.
   *
   * <ul>
   *   <li>When {@code true}.
   *   <li>Then return Complex.
   * </ul>
   *
   * <p>Methods under test:
   *
   * <ul>
   *   <li>{@link SQLDatabasePersistAction#SQLDatabasePersistAction(String, String, ActionType,
   *       boolean)}
   *   <li>{@link SQLDatabasePersistAction#afterExecute(DBCSession, Throwable)}
   *   <li>{@link SQLDatabasePersistAction#beforeExecute(DBCSession)}
   *   <li>{@link SQLDatabasePersistAction#getScript()}
   *   <li>{@link SQLDatabasePersistAction#getTitle()}
   *   <li>{@link SQLDatabasePersistAction#getType()}
   *   <li>{@link SQLDatabasePersistAction#isComplex()}
   * </ul>
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({
    "void SQLDatabasePersistAction.<init>(String, String, ActionType)",
    "void SQLDatabasePersistAction.<init>(String, String, ActionType, boolean)",
    "void SQLDatabasePersistAction.afterExecute(DBCSession, Throwable)",
    "void SQLDatabasePersistAction.beforeExecute(DBCSession)",
    "String SQLDatabasePersistAction.getScript()",
    "String SQLDatabasePersistAction.getTitle()",
    "ActionType SQLDatabasePersistAction.getType()",
    "boolean SQLDatabasePersistAction.isComplex()"
  })
  public void testGettersAndSetters_whenTrue_thenReturnComplex() throws DBCException {
    // Arrange and Act
    SQLDatabasePersistAction actualSqlDatabasePersistAction =
        new SQLDatabasePersistAction("Dr", "Script", ActionType.INITIALIZER, true);
    DBCSession session = mock(DBCSession.class);
    actualSqlDatabasePersistAction.afterExecute(session, new Throwable());
    actualSqlDatabasePersistAction.beforeExecute(mock(DBCSession.class));
    String actualScript = actualSqlDatabasePersistAction.getScript();
    String actualTitle = actualSqlDatabasePersistAction.getTitle();
    ActionType actualType = actualSqlDatabasePersistAction.getType();

    // Assert
    assertEquals("Dr", actualTitle);
    assertEquals("Script", actualScript);
    assertEquals(ActionType.INITIALIZER, actualType);
    assertTrue(actualSqlDatabasePersistAction.isComplex());
  }

  /**
   * Test {@link SQLDatabasePersistAction#SQLDatabasePersistAction(String)}.
   *
   * <p>Method under test: {@link SQLDatabasePersistAction#SQLDatabasePersistAction(String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistAction.<init>(String)"})
  public void testNewSQLDatabasePersistAction() {
    // Arrange and Act
    SQLDatabasePersistAction actualSqlDatabasePersistAction =
        new SQLDatabasePersistAction("Script");

    // Assert
    assertEquals("", actualSqlDatabasePersistAction.getTitle());
    assertEquals("Script", actualSqlDatabasePersistAction.getScript());
    assertEquals(ActionType.NORMAL, actualSqlDatabasePersistAction.getType());
    assertFalse(actualSqlDatabasePersistAction.isComplex());
  }

  /**
   * Test {@link SQLDatabasePersistAction#SQLDatabasePersistAction(String, String)}.
   *
   * <p>Method under test: {@link SQLDatabasePersistAction#SQLDatabasePersistAction(String, String)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistAction.<init>(String, String)"})
  public void testNewSQLDatabasePersistAction2() {
    // Arrange and Act
    SQLDatabasePersistAction actualSqlDatabasePersistAction =
        new SQLDatabasePersistAction("Dr", "Script");

    // Assert
    assertEquals("Dr", actualSqlDatabasePersistAction.getTitle());
    assertEquals("Script", actualSqlDatabasePersistAction.getScript());
    assertEquals(ActionType.NORMAL, actualSqlDatabasePersistAction.getType());
    assertFalse(actualSqlDatabasePersistAction.isComplex());
  }

  /**
   * Test {@link SQLDatabasePersistAction#SQLDatabasePersistAction(String, String, boolean)}.
   *
   * <p>Method under test: {@link SQLDatabasePersistAction#SQLDatabasePersistAction(String, String,
   * boolean)}
   */
  @Test
  @Category(ContributionFromDiffblue.class)
  @ManagedByDiffblue
  @MethodsUnderTest({"void SQLDatabasePersistAction.<init>(String, String, boolean)"})
  public void testNewSQLDatabasePersistAction3() {
    // Arrange and Act
    SQLDatabasePersistAction actualSqlDatabasePersistAction =
        new SQLDatabasePersistAction("Dr", "Script", true);

    // Assert
    assertEquals("Dr", actualSqlDatabasePersistAction.getTitle());
    assertEquals("Script", actualSqlDatabasePersistAction.getScript());
    assertEquals(ActionType.NORMAL, actualSqlDatabasePersistAction.getType());
    assertTrue(actualSqlDatabasePersistAction.isComplex());
  }
}
